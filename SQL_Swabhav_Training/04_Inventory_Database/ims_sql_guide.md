# Inventory Management System — Database Design & SQL Problem Set

> **RDBMS Target:** PostgreSQL 15+ (syntax notes for MySQL provided where they differ)

---

## Part 1 — Database Design Description

### Overview

The Inventory Management System (IMS) is a relational database designed to track the full lifecycle of a product — from supplier procurement through warehouse storage to customer sale. It supports multiple warehouses, multiple suppliers per product, FIFO/LIFO valuation strategies, and a complete audit trail of every stock movement.

### Core Entities & Relationships

| Entity | Role |
|---|---|
| **Categories** | Hierarchical product classification (self-referencing) |
| **Products** | Master product catalog with SKU, pricing, and tax classification |
| **Suppliers** | Vendor master with contact and payment terms |
| **ProductSuppliers** | Many-to-many: which suppliers carry which products, at what cost |
| **Warehouses** | Physical storage locations with capacity tracking |
| **WarehouseZones** | Sub-areas within a warehouse (Aisle, Rack, Bin) |
| **Inventory** | Current on-hand quantity per product per warehouse zone |
| **Customers** | Customer master with credit limit and tier |
| **PurchaseOrders** | Inbound orders from suppliers |
| **PurchaseOrderItems** | Line items on each purchase order |
| **SalesOrders** | Outbound orders to customers |
| **SalesOrderItems** | Line items on each sales order |
| **StockTransactions** | Immutable ledger of every inventory movement |
| **AuditLog** | Row-level change log driven by triggers |

### Key Design Decisions

1. **Hierarchical Categories** — `parent_category_id` self-reference enables recursive tree queries (recursive CTEs).
2. **Inventory at Zone Level** — stock is tracked per `warehouse_zone_id`, enabling bin-level accuracy.
3. **Immutable Transaction Ledger** — `StockTransactions` is append-only; current inventory is always derivable from it, ensuring auditability.
4. **Soft Deletes** — Products and Customers use an `is_active` flag rather than hard deletes.
5. **Optimistic Locking** — `Inventory` has a `version` column for concurrency control.

---

## Part 2 — Database Schema

```sql
-- ============================================================
--  INVENTORY MANAGEMENT SYSTEM — SCHEMA
--  Target: PostgreSQL 15+
-- ============================================================

-- ── Extensions ────────────────────────────────────────────
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS pg_trgm;       -- for fuzzy product search

-- ── 1. Categories (self-referencing hierarchy) ─────────────
CREATE TABLE Categories (
    category_id     SERIAL          PRIMARY KEY,
    category_name   VARCHAR(100)    NOT NULL,
    parent_category_id INT          REFERENCES Categories(category_id) ON DELETE SET NULL,
    description     TEXT,
    created_at      TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    CONSTRAINT uq_category_name_parent UNIQUE (category_name, parent_category_id)
);

-- ── 2. Suppliers ───────────────────────────────────────────
CREATE TABLE Suppliers (
    supplier_id     SERIAL          PRIMARY KEY,
    supplier_name   VARCHAR(200)    NOT NULL,
    contact_name    VARCHAR(100),
    email           VARCHAR(150)    UNIQUE,
    phone           VARCHAR(30),
    address         TEXT,
    city            VARCHAR(80),
    country         VARCHAR(80)     NOT NULL DEFAULT 'India',
    payment_terms   INT             NOT NULL DEFAULT 30  -- days
        CHECK (payment_terms BETWEEN 0 AND 365),
    rating          NUMERIC(3,2)    CHECK (rating BETWEEN 0 AND 5),
    is_active       BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);

-- ── 3. Products ────────────────────────────────────────────
CREATE TABLE Products (
    product_id      SERIAL          PRIMARY KEY,
    sku             VARCHAR(50)     UNIQUE NOT NULL,
    product_name    VARCHAR(255)    NOT NULL,
    description     TEXT,
    category_id     INT             REFERENCES Categories(category_id),
    unit_price      NUMERIC(12,2)   NOT NULL CHECK (unit_price >= 0),
    cost_price      NUMERIC(12,2)   NOT NULL CHECK (cost_price >= 0),
    tax_rate        NUMERIC(5,2)    NOT NULL DEFAULT 18.00 CHECK (tax_rate BETWEEN 0 AND 100),
    unit_of_measure VARCHAR(20)     NOT NULL DEFAULT 'PCS',
    weight_kg       NUMERIC(8,3),
    is_active       BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);

-- ── 4. ProductSuppliers (junction) ────────────────────────
CREATE TABLE ProductSuppliers (
    product_id      INT             NOT NULL REFERENCES Products(product_id),
    supplier_id     INT             NOT NULL REFERENCES Suppliers(supplier_id),
    supplier_sku    VARCHAR(50),
    unit_cost       NUMERIC(12,2)   NOT NULL CHECK (unit_cost >= 0),
    lead_time_days  INT             NOT NULL DEFAULT 7 CHECK (lead_time_days >= 0),
    is_preferred    BOOLEAN         NOT NULL DEFAULT FALSE,
    PRIMARY KEY (product_id, supplier_id)
);

-- ── 5. Warehouses ──────────────────────────────────────────
CREATE TABLE Warehouses (
    warehouse_id    SERIAL          PRIMARY KEY,
    warehouse_name  VARCHAR(150)    NOT NULL,
    address         TEXT,
    city            VARCHAR(80),
    state           VARCHAR(80),
    pincode         VARCHAR(10),
    capacity_sqft   NUMERIC(10,2),
    manager_name    VARCHAR(100),
    is_active       BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);

-- ── 6. WarehouseZones ──────────────────────────────────────
CREATE TABLE WarehouseZones (
    zone_id         SERIAL          PRIMARY KEY,
    warehouse_id    INT             NOT NULL REFERENCES Warehouses(warehouse_id),
    zone_code       VARCHAR(20)     NOT NULL,       -- e.g. "A-01-03" (Aisle-Rack-Bin)
    zone_type       VARCHAR(30)     NOT NULL DEFAULT 'STORAGE'
        CHECK (zone_type IN ('STORAGE','RECEIVING','DISPATCH','QUARANTINE')),
    max_capacity    INT             CHECK (max_capacity > 0),
    UNIQUE (warehouse_id, zone_code)
);

-- ── 7. Inventory ───────────────────────────────────────────
CREATE TABLE Inventory (
    inventory_id    SERIAL          PRIMARY KEY,
    product_id      INT             NOT NULL REFERENCES Products(product_id),
    zone_id         INT             NOT NULL REFERENCES WarehouseZones(zone_id),
    quantity_on_hand INT            NOT NULL DEFAULT 0 CHECK (quantity_on_hand >= 0),
    reorder_level   INT             NOT NULL DEFAULT 10 CHECK (reorder_level >= 0),
    reorder_qty     INT             NOT NULL DEFAULT 50 CHECK (reorder_qty > 0),
    last_counted_at TIMESTAMPTZ,
    version         INT             NOT NULL DEFAULT 0,  -- optimistic locking
    UNIQUE (product_id, zone_id)
);

-- ── 8. Customers ───────────────────────────────────────────
CREATE TABLE Customers (
    customer_id     SERIAL          PRIMARY KEY,
    customer_name   VARCHAR(200)    NOT NULL,
    email           VARCHAR(150)    UNIQUE,
    phone           VARCHAR(30),
    address         TEXT,
    city            VARCHAR(80),
    state           VARCHAR(80),
    pincode         VARCHAR(10),
    customer_tier   VARCHAR(20)     NOT NULL DEFAULT 'STANDARD'
        CHECK (customer_tier IN ('STANDARD','SILVER','GOLD','PLATINUM')),
    credit_limit    NUMERIC(14,2)   NOT NULL DEFAULT 0 CHECK (credit_limit >= 0),
    is_active       BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);

-- ── 9. PurchaseOrders ──────────────────────────────────────
CREATE TABLE PurchaseOrders (
    po_id           SERIAL          PRIMARY KEY,
    po_number       VARCHAR(30)     UNIQUE NOT NULL,
    supplier_id     INT             NOT NULL REFERENCES Suppliers(supplier_id),
    warehouse_id    INT             NOT NULL REFERENCES Warehouses(warehouse_id),
    status          VARCHAR(20)     NOT NULL DEFAULT 'DRAFT'
        CHECK (status IN ('DRAFT','SUBMITTED','PARTIAL','RECEIVED','CANCELLED')),
    order_date      DATE            NOT NULL DEFAULT CURRENT_DATE,
    expected_date   DATE,
    received_date   DATE,
    total_amount    NUMERIC(14,2)   GENERATED ALWAYS AS STORED (NULL),  -- updated by trigger
    notes           TEXT,
    created_by      VARCHAR(80),
    created_at      TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    CONSTRAINT chk_po_dates CHECK (expected_date IS NULL OR expected_date >= order_date)
);

-- NOTE: PostgreSQL does not support expression-based GENERATED ALWAYS for totals in
-- the same way without a trigger. Remove the GENERATED column and manage via trigger.
ALTER TABLE PurchaseOrders DROP COLUMN total_amount;
ALTER TABLE PurchaseOrders ADD COLUMN total_amount NUMERIC(14,2) NOT NULL DEFAULT 0;

-- ── 10. PurchaseOrderItems ─────────────────────────────────
CREATE TABLE PurchaseOrderItems (
    poi_id          SERIAL          PRIMARY KEY,
    po_id           INT             NOT NULL REFERENCES PurchaseOrders(po_id) ON DELETE CASCADE,
    product_id      INT             NOT NULL REFERENCES Products(product_id),
    ordered_qty     INT             NOT NULL CHECK (ordered_qty > 0),
    received_qty    INT             NOT NULL DEFAULT 0 CHECK (received_qty >= 0),
    unit_cost       NUMERIC(12,2)   NOT NULL CHECK (unit_cost >= 0),
    line_total      NUMERIC(14,2)   NOT NULL GENERATED ALWAYS AS (ordered_qty * unit_cost) STORED,
    UNIQUE (po_id, product_id),
    CONSTRAINT chk_received_lte_ordered CHECK (received_qty <= ordered_qty)
);

-- ── 11. SalesOrders ────────────────────────────────────────
CREATE TABLE SalesOrders (
    so_id           SERIAL          PRIMARY KEY,
    so_number       VARCHAR(30)     UNIQUE NOT NULL,
    customer_id     INT             NOT NULL REFERENCES Customers(customer_id),
    warehouse_id    INT             NOT NULL REFERENCES Warehouses(warehouse_id),
    status          VARCHAR(20)     NOT NULL DEFAULT 'PENDING'
        CHECK (status IN ('PENDING','CONFIRMED','PICKING','SHIPPED','DELIVERED','CANCELLED','RETURNED')),
    order_date      TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    required_date   DATE,
    shipped_date    TIMESTAMPTZ,
    discount_pct    NUMERIC(5,2)    NOT NULL DEFAULT 0 CHECK (discount_pct BETWEEN 0 AND 100),
    total_amount    NUMERIC(14,2)   NOT NULL DEFAULT 0,
    notes           TEXT,
    created_by      VARCHAR(80),
    created_at      TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);

-- ── 12. SalesOrderItems ────────────────────────────────────
CREATE TABLE SalesOrderItems (
    soi_id          SERIAL          PRIMARY KEY,
    so_id           INT             NOT NULL REFERENCES SalesOrders(so_id) ON DELETE CASCADE,
    product_id      INT             NOT NULL REFERENCES Products(product_id),
    quantity        INT             NOT NULL CHECK (quantity > 0),
    unit_price      NUMERIC(12,2)   NOT NULL CHECK (unit_price >= 0),
    discount_pct    NUMERIC(5,2)    NOT NULL DEFAULT 0 CHECK (discount_pct BETWEEN 0 AND 100),
    line_total      NUMERIC(14,2)   NOT NULL
        GENERATED ALWAYS AS (quantity * unit_price * (1 - discount_pct / 100.0)) STORED,
    UNIQUE (so_id, product_id)
);

-- ── 13. StockTransactions (immutable ledger) ───────────────
CREATE TABLE StockTransactions (
    txn_id          BIGSERIAL       PRIMARY KEY,
    txn_type        VARCHAR(30)     NOT NULL
        CHECK (txn_type IN (
            'PURCHASE_RECEIPT','SALE_DISPATCH','TRANSFER_IN','TRANSFER_OUT',
            'ADJUSTMENT_ADD','ADJUSTMENT_SUB','RETURN_FROM_CUSTOMER',
            'RETURN_TO_SUPPLIER','OPENING_STOCK','DAMAGE_WRITE_OFF'
        )),
    product_id      INT             NOT NULL REFERENCES Products(product_id),
    zone_id         INT             NOT NULL REFERENCES WarehouseZones(zone_id),
    reference_type  VARCHAR(20),    -- 'PO','SO','MANUAL'
    reference_id    INT,            -- po_id or so_id
    quantity_change INT             NOT NULL,  -- positive = IN, negative = OUT
    unit_cost       NUMERIC(12,2),
    txn_date        TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    performed_by    VARCHAR(80),
    notes           TEXT
);

-- ── 14. AuditLog ───────────────────────────────────────────
CREATE TABLE AuditLog (
    log_id          BIGSERIAL       PRIMARY KEY,
    table_name      VARCHAR(80)     NOT NULL,
    record_id       INT             NOT NULL,
    operation       CHAR(1)         NOT NULL CHECK (operation IN ('I','U','D')),
    changed_by      VARCHAR(80),
    changed_at      TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
    old_data        JSONB,
    new_data        JSONB
);

-- ============================================================
--  INDEXES
-- ============================================================
CREATE INDEX idx_products_category    ON Products(category_id);
CREATE INDEX idx_products_sku         ON Products USING gin(sku gin_trgm_ops);
CREATE INDEX idx_products_name_trgm   ON Products USING gin(product_name gin_trgm_ops);
CREATE INDEX idx_inventory_product    ON Inventory(product_id);
CREATE INDEX idx_inventory_zone       ON Inventory(zone_id);
CREATE INDEX idx_inventory_low_stock  ON Inventory(product_id) WHERE quantity_on_hand <= reorder_level;
CREATE INDEX idx_po_supplier          ON PurchaseOrders(supplier_id);
CREATE INDEX idx_po_status_date       ON PurchaseOrders(status, order_date);
CREATE INDEX idx_so_customer          ON SalesOrders(customer_id);
CREATE INDEX idx_so_status_date       ON SalesOrders(status, order_date);
CREATE INDEX idx_txn_product_date     ON StockTransactions(product_id, txn_date DESC);
CREATE INDEX idx_txn_zone             ON StockTransactions(zone_id);
CREATE INDEX idx_txn_reference        ON StockTransactions(reference_type, reference_id);
CREATE INDEX idx_audit_table_record   ON AuditLog(table_name, record_id);

-- ============================================================
--  TRIGGER: Update PurchaseOrder.total_amount
-- ============================================================
CREATE OR REPLACE FUNCTION fn_update_po_total()
RETURNS TRIGGER LANGUAGE plpgsql AS $$
BEGIN
    UPDATE PurchaseOrders
    SET total_amount = (
        SELECT COALESCE(SUM(line_total), 0)
        FROM PurchaseOrderItems
        WHERE po_id = COALESCE(NEW.po_id, OLD.po_id)
    )
    WHERE po_id = COALESCE(NEW.po_id, OLD.po_id);
    RETURN NULL;
END;
$$;

CREATE TRIGGER trg_po_total
AFTER INSERT OR UPDATE OR DELETE ON PurchaseOrderItems
FOR EACH ROW EXECUTE FUNCTION fn_update_po_total();

-- ============================================================
--  TRIGGER: Update SalesOrder.total_amount
-- ============================================================
CREATE OR REPLACE FUNCTION fn_update_so_total()
RETURNS TRIGGER LANGUAGE plpgsql AS $$
BEGIN
    UPDATE SalesOrders
    SET total_amount = (
        SELECT COALESCE(SUM(line_total), 0)
        FROM SalesOrderItems
        WHERE so_id = COALESCE(NEW.so_id, OLD.so_id)
    )
    WHERE so_id = COALESCE(NEW.so_id, OLD.so_id);
    RETURN NULL;
END;
$$;

CREATE TRIGGER trg_so_total
AFTER INSERT OR UPDATE OR DELETE ON SalesOrderItems
FOR EACH ROW EXECUTE FUNCTION fn_update_so_total();

-- ============================================================
--  TRIGGER: Audit logging for Products
-- ============================================================
CREATE OR REPLACE FUNCTION fn_audit_products()
RETURNS TRIGGER LANGUAGE plpgsql AS $$
BEGIN
    INSERT INTO AuditLog(table_name, record_id, operation, changed_by, old_data, new_data)
    VALUES (
        'Products',
        COALESCE(NEW.product_id, OLD.product_id),
        LEFT(TG_OP, 1),
        current_user,
        CASE WHEN TG_OP <> 'INSERT' THEN row_to_json(OLD)::jsonb END,
        CASE WHEN TG_OP <> 'DELETE' THEN row_to_json(NEW)::jsonb END
    );
    RETURN COALESCE(NEW, OLD);
END;
$$;

CREATE TRIGGER trg_audit_products
AFTER INSERT OR UPDATE OR DELETE ON Products
FOR EACH ROW EXECUTE FUNCTION fn_audit_products();
```

---

## Part 3 — SQL Problem Statements

Problems are grouped by topic. Each builds on the schema above.

---

### Section A — Basic CRUD & Filtering

---

**P001 · Basic SELECT with Filtering**
*Topic: CRUD / WHERE*

Retrieve all active products in the "Electronics" category whose selling price is greater than ₹5,000 and whose cost price gives a margin of at least 20%. Display: `sku`, `product_name`, `unit_price`, `cost_price`, and calculated `margin_pct`.

**Hint:** `margin_pct = (unit_price - cost_price) / unit_price * 100`

---

**P002 · Fuzzy Product Search**
*Topic: ILIKE / pg_trgm*

Write a query to find products whose `product_name` contains the word "cable" (case-insensitive). Order results by similarity score (highest first) using the `similarity()` function from pg_trgm. Return `product_id`, `product_name`, `sku`.

---

**P003 · Bulk Status Update**
*Topic: UPDATE with subquery*

A supplier (supplier_id = 7) has gone bankrupt. Write a single `UPDATE` statement to mark all `PurchaseOrders` in `DRAFT` or `SUBMITTED` status for that supplier as `CANCELLED`, and set `notes = 'Auto-cancelled: supplier inactive'`.

---

**P004 · Soft Delete with Cascade Effect**
*Topic: UPDATE / conditional logic*

Deactivate all products (`is_active = FALSE`) that have never appeared in any `SalesOrderItems` row AND have had no `StockTransactions` in the last 365 days. Return the count of products deactivated.

---

**P005 · Insert with Conflict Handling**
*Topic: UPSERT / ON CONFLICT*

Write an `INSERT ... ON CONFLICT DO UPDATE` statement that adds a product–supplier relationship to `ProductSuppliers`. If the relationship already exists, update the `unit_cost` and `lead_time_days` to the new values but only if the new `unit_cost` is lower than the existing one.

---

### Section B — Joins

---

**P006 · Multi-Table Inner Join**
*Topic: INNER JOIN*

For every `RECEIVED` purchase order placed in the current calendar year, display: `po_number`, `supplier_name`, `warehouse_name`, `product_name`, `ordered_qty`, `received_qty`, and `line_total`. Sort by `po_number`, then `product_name`.

---

**P007 · Products Never Ordered**
*Topic: LEFT JOIN / NULL check*

Find all active products that have never been included in any `SalesOrderItems`. Display `product_id`, `sku`, `product_name`, and the number of days since the product was created (`age_days`).

---

**P008 · Preferred Supplier per Product**
*Topic: JOIN + conditional aggregation*

For each active product, show its preferred supplier's name and unit cost. If no preferred supplier exists (`is_preferred = FALSE` for all rows), show `'No preferred supplier'` and `NULL` for cost. Use a single query with a `LEFT JOIN`.

---

**P009 · Full Outer Join — Stock Discrepancy**
*Topic: FULL OUTER JOIN*

You have two data sources: (a) `Inventory` (current on-hand) and (b) a physical count table you create as a CTE with hard-coded sample values for 5 products. Do a `FULL OUTER JOIN` to find: products in `Inventory` but missing from the count, products in the count but missing from `Inventory`, and products whose counts differ. Label each discrepancy type.

---

**P010 · Self-Join — Category Hierarchy Display**
*Topic: Self-Join*

Using a self-join on `Categories`, display each category alongside its parent category name (show `'Root'` when `parent_category_id IS NULL`). Include: `category_id`, `category_name`, `parent_name`, `depth` (0 for root, 1 for child).

---

### Section C — Aggregation & GROUP BY

---

**P011 · Monthly Revenue Summary**
*Topic: DATE_TRUNC + GROUP BY*

Compute monthly revenue for the past 12 months from `SalesOrders` (status IN `SHIPPED`, `DELIVERED`). For each month, show: `month` (formatted `YYYY-MM`), `order_count`, `total_revenue`, `avg_order_value`, and `revenue_growth_pct` compared to the previous month.

**Challenge:** Compute `revenue_growth_pct` without a subquery — use a window function.

---

**P012 · Top-N Products per Category**
*Topic: GROUP BY + HAVING*

Find the top 3 best-selling products (by total quantity dispatched in `StockTransactions` with `txn_type = 'SALE_DISPATCH'`) within each product category. Show `category_name`, `product_name`, `total_qty_sold`, and `rank_in_category`.

---

**P013 · Supplier Performance Scorecard**
*Topic: Aggregation + CASE*

For each supplier, calculate:
- Total POs raised
- Total POs fully received (where `status = 'RECEIVED'`)
- On-time delivery rate: percentage of received POs where `received_date <= expected_date`
- Average days late (only for late deliveries; NULL if none)
- A `performance_grade` using CASE: `A` (≥90% on-time), `B` (70–89%), `C` (<70%)

---

**P014 · Inventory Valuation by Warehouse**
*Topic: GROUP BY + JOIN*

Calculate the total inventory value (quantity_on_hand × cost_price) per warehouse. Display `warehouse_name`, `total_skus` (distinct product count), `total_units`, `total_value`, and the percentage of total company-wide inventory value each warehouse holds.

---

**P015 · Dead Stock Detection**
*Topic: Aggregation + date arithmetic*

Identify products that are "dead stock": on-hand quantity > 0 but no `SALE_DISPATCH` transaction in the last 90 days. For each such product, show `product_name`, `total_on_hand` (across all zones), `last_sale_date`, and `estimated_value` (quantity × cost_price).

---

### Section D — Subqueries

---

**P016 · Correlated Subquery — Below-Average Stock**
*Topic: Correlated subquery*

For each product, use a correlated subquery to check whether its total on-hand quantity is below the average on-hand quantity across all products in the same category. Return only products that are below average, showing `product_name`, `category_name`, `on_hand`, and `category_avg`.

---

**P017 · Non-Correlated Subquery — High-Value Orders**
*Topic: Non-correlated subquery / IN*

Find all customers who have placed at least one sales order with a `total_amount` exceeding the 90th percentile of all sales order values. Show `customer_name`, `customer_tier`, `email`, and their own `max_order_value`.

---

**P018 · Scalar Subquery in SELECT**
*Topic: Scalar subquery*

List every warehouse with:
- Its name and city
- The count of distinct products currently stocked
- A scalar subquery showing the name of the product with the highest on-hand quantity in that warehouse

---

**P019 · EXISTS vs IN Performance**
*Topic: EXISTS / query semantics*

Write two semantically equivalent queries to find suppliers who have supplied at least one product that has been sold (i.e., appears in `SalesOrderItems`):
- Version A: Using `IN` with a subquery
- Version B: Using `EXISTS`

Then write a short comment explaining which version PostgreSQL's planner typically prefers and why.

---

### Section E — CTEs & Recursive Queries

---

**P020 · Non-Recursive CTE — Reorder Report**
*Topic: CTE*

Using a CTE named `low_stock`, identify all product–zone combinations where `quantity_on_hand <= reorder_level`. Then join with `ProductSuppliers` (preferred supplier) and `Suppliers` to produce a reorder recommendation report showing: `product_name`, `zone_code`, `warehouse_name`, `on_hand`, `reorder_level`, `reorder_qty`, `preferred_supplier`, `supplier_email`, `lead_time_days`.

---

**P021 · Recursive CTE — Full Category Tree**
*Topic: Recursive CTE*

Write a recursive CTE to display the full category hierarchy as a tree. Output columns: `category_id`, `category_name`, `full_path` (e.g., `"Electronics > Cables > USB-C"`), `depth`, and `is_leaf` (TRUE if no children exist).

---

**P022 · Multi-Step CTE Pipeline**
*Topic: Chained CTEs*

Using three chained CTEs:
1. `monthly_sales` — total revenue per customer per month (last 6 months)
2. `customer_stats` — for each customer: avg monthly revenue, max monthly revenue, months active
3. `segmented` — label each customer as `'Champion'` (avg > ₹1L, months = 6), `'Loyal'` (avg > ₹50K), `'At Risk'` (months < 3), or `'Standard'`

Final SELECT: display customer name, tier, segment label, avg_monthly_revenue.

---

**P023 · CTE with Data Modification (Writable CTE)**
*Topic: WITH ... INSERT/UPDATE*

Using a writable CTE, atomically:
1. Select all `SUBMITTED` POs that are past their `expected_date` by more than 7 days
2. Insert a row into `AuditLog` for each such PO (operation = `'U'`, new_data = `'{"status":"overdue_flagged"}'`)
3. Update those POs' notes to append `' | OVERDUE'`

---

### Section F — Window Functions

---

**P024 · Running Totals & Cumulative Stock**
*Topic: SUM() OVER (ORDER BY)*

For a given product (use product_id = 1 or a parameter), show the full transaction history from `StockTransactions` ordered by `txn_date`. For each row display: `txn_date`, `txn_type`, `quantity_change`, and `running_balance` (cumulative sum of `quantity_change`).

---

**P025 · RANK vs DENSE_RANK vs ROW_NUMBER**
*Topic: Ranking window functions*

On the `SalesOrders` table, for each customer compute their orders ranked by `total_amount` descending. Display all three ranking functions side-by-side (`RANK`, `DENSE_RANK`, `ROW_NUMBER`) and identify rows where they produce different results. Include `customer_name`, `so_number`, `total_amount`.

---

**P026 · Moving Average — Sales Smoothing**
*Topic: AVG() OVER (ROWS BETWEEN)*

Calculate a 3-month centred moving average of monthly sales revenue (use `DATE_TRUNC('month', order_date)`). Display: `month`, `monthly_revenue`, `moving_avg_3m`. Exclude months with no sales from the average window. Handle edge months (first and last) gracefully using `ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING`.

---

**P027 · LAG/LEAD — Month-over-Month Change**
*Topic: LAG() / LEAD()*

For each product category, show month-by-month sales revenue alongside: `prev_month_revenue` (using `LAG`), `next_month_revenue` (using `LEAD`), `mom_change_pct`, and a `trend` label: `'UP'`, `'DOWN'`, or `'FLAT'` (< 1% change).

---

**P028 · NTILE — Customer Quartile Segmentation**
*Topic: NTILE()*

Divide all customers into 4 equal quartiles based on their total lifetime spending (sum of all delivered/shipped sales order values). For each quartile, show: quartile number, number of customers, min spend, max spend, avg spend. Add a label: Q1 = `'Low Value'`, Q2 = `'Mid Value'`, Q3 = `'High Value'`, Q4 = `'Top Tier'`.

---

### Section G — Views

---

**P029 · Materialized View — Daily Inventory Snapshot**
*Topic: MATERIALIZED VIEW*

Create a materialized view `mv_daily_inventory` that shows, for each product and warehouse, the current on-hand quantity (summed across all zones), reorder status (`'OK'`/`'LOW'`/`'CRITICAL'` — critical if qty < reorder_level / 2), and total inventory value. Write the `CREATE MATERIALIZED VIEW` statement and the `REFRESH MATERIALIZED VIEW CONCURRENTLY` command.

---

**P030 · View with Window Functions — Supplier Ranking**
*Topic: VIEW + window functions*

Create a view `vw_supplier_ranking` that ranks suppliers within each product category by their total value of goods delivered (received POs). The view should expose: `category_name`, `supplier_name`, `total_delivered_value`, `rank_in_category`, `pct_of_category_total`. Then write a query against the view to find all rank-1 suppliers.

---

**P031 · Updatable View — Product Pricing Interface**
*Topic: Updatable VIEW / INSTEAD OF trigger*

Create a view `vw_product_pricing` that joins `Products` with `Categories` and exposes: `product_id`, `sku`, `product_name`, `category_name`, `unit_price`, `cost_price`, `margin_pct`. Write an `INSTEAD OF UPDATE` trigger on this view so that when a user updates `unit_price`, it applies to the base `Products` table and logs the change to `AuditLog`.

---

### Section H — Stored Procedures & Functions

---

**P032 · UDF — Calculate Customer Credit Exposure**
*Topic: User-Defined Function*

Write a function `fn_customer_credit_exposure(p_customer_id INT) RETURNS NUMERIC` that computes a customer's outstanding exposure: sum of `total_amount` for all sales orders in status `PENDING`, `CONFIRMED`, `PICKING`, or `SHIPPED`. The function should also return whether the exposure exceeds the customer's `credit_limit` as an OUT parameter (or as a second column using a composite return type).

---

**P033 · Stored Procedure — Receive Purchase Order**
*Topic: Stored Procedure with transaction control*

Write a stored procedure `sp_receive_po(p_po_id INT, p_received_items JSONB)` that:
1. Validates the PO is in `SUBMITTED` or `PARTIAL` status
2. For each item in the JSON array `[{"product_id":1,"qty":50,"zone_id":3}, ...]`:
   - Updates `PurchaseOrderItems.received_qty`
   - Upserts `Inventory` (quantity_on_hand += qty)
   - Inserts a `StockTransactions` row (`txn_type = 'PURCHASE_RECEIPT'`)
3. Updates `PurchaseOrders.status` to `RECEIVED` if all items are fully received, else `PARTIAL`
4. Wraps everything in a `BEGIN ... EXCEPTION ... ROLLBACK` block

---

**P034 · Function — FIFO Cost Calculation**
*Topic: UDF + window functions*

Write a function `fn_fifo_cost_of_goods_sold(p_product_id INT, p_qty_to_sell INT) RETURNS NUMERIC` that simulates FIFO by reading `PURCHASE_RECEIPT` transactions ordered by `txn_date` and calculating the weighted cost of the oldest stock layers needed to fulfil the quantity. Use a loop or recursive CTE internally.

---

**P035 · Procedure — Reorder Automation**
*Topic: Stored Procedure + dynamic SQL*

Write a procedure `sp_auto_generate_reorders()` that:
1. Finds all product–warehouse combinations where on-hand ≤ reorder_level
2. For each, finds the preferred supplier via `ProductSuppliers`
3. Generates a `PurchaseOrder` (status = `DRAFT`) and corresponding `PurchaseOrderItems` rows using `reorder_qty` as ordered_qty and supplier's `unit_cost`
4. Skips products that already have an open PO (DRAFT/SUBMITTED) for the same supplier

---

### Section I — Query Optimization

---

**P036 · EXPLAIN ANALYZE — Index Usage**
*Topic: Query plan analysis*

Write the following query and then prefix it with `EXPLAIN (ANALYZE, BUFFERS, FORMAT TEXT)`:

```sql
SELECT p.product_name, SUM(st.quantity_change) AS total_sold
FROM StockTransactions st
JOIN Products p ON p.product_id = st.product_id
WHERE st.txn_type = 'SALE_DISPATCH'
  AND st.txn_date >= NOW() - INTERVAL '90 days'
GROUP BY p.product_name
ORDER BY total_sold DESC
LIMIT 20;
```

Identify: (a) whether `idx_txn_product_date` is used, (b) the join strategy chosen, (c) what additional index (if any) would further reduce cost, and create it.

---

**P037 · Rewrite Correlated Subquery as JOIN**
*Topic: Optimization / query rewrite*

The following slow query uses a correlated subquery:

```sql
SELECT product_id, product_name
FROM Products p
WHERE (
    SELECT COALESCE(SUM(quantity_on_hand), 0)
    FROM Inventory i
    WHERE i.product_id = p.product_id
) < 50;
```

Rewrite it as a `LEFT JOIN ... GROUP BY ... HAVING` query. Verify both return identical results, then use `EXPLAIN` to compare the estimated costs.

---

**P038 · Partial Index for Hot Queries**
*Topic: Partial index design*

Your application frequently queries for sales orders in `PENDING` or `CONFIRMED` status (the "active" orders). The `SalesOrders` table has millions of rows but only ~2% are active. Design and create a partial index that makes this query fast:

```sql
SELECT so_id, customer_id, total_amount
FROM SalesOrders
WHERE status IN ('PENDING', 'CONFIRMED')
ORDER BY order_date;
```

Explain why a partial index is more efficient than a full index on `(status, order_date)`.

---

**P039 · CTE Fence vs Subquery**
*Topic: CTE optimization (PostgreSQL)*

PostgreSQL (pre-v12) treats CTEs as optimisation fences. Demonstrate this by:
1. Writing a query with a CTE that filters `StockTransactions` to the last 30 days, then joins with `Products`
2. Writing the equivalent query using a subquery in the FROM clause
3. Running `EXPLAIN` on both and comparing whether the date filter is pushed down to the index scan

Note: In PostgreSQL 12+, use `WITH ... AS MATERIALIZED` vs `WITH ... AS NOT MATERIALIZED` to control this behaviour explicitly.

---

**P040 · Batch Insert Performance**
*Topic: Bulk DML / performance*

You need to insert 10,000 `StockTransactions` rows. Compare three approaches:
- A: Single-row `INSERT` in a loop (demonstrate as pseudo-code)
- B: Multi-row `INSERT ... VALUES (),(),()`
- C: `COPY FROM STDIN` (PostgreSQL bulk loader)

Write approach B as a real SQL statement with at least 5 sample rows. Then write the steps to use `COPY` from a CSV file named `transactions.csv`. Explain the performance difference in terms of WAL writes, round-trips, and index maintenance.

---

### Section J — Interconnected / Advanced Problems

---

**P041 · CTE inside a View — Product Velocity Report**
*Topic: CTE + VIEW*

Create a view `vw_product_velocity` that, using an internal CTE, classifies each product by its "velocity" over the last 30 days:
- `'FAST'` — sold more than 100 units
- `'MEDIUM'` — sold 20–100 units  
- `'SLOW'` — sold 1–19 units
- `'DEAD'` — sold 0 units

The view should also show `days_of_stock_remaining = on_hand / daily_avg_sales` (NULL if dead).

---

**P042 · Window Function inside a CTE inside a Procedure**
*Topic: All concepts chained*

Write a stored procedure `sp_generate_abc_report(p_warehouse_id INT)` that:
1. Uses a CTE with a window function (`PERCENT_RANK`) to rank products by their 90-day sales value within the given warehouse
2. Classifies: A = top 20% of revenue (by value), B = next 30%, C = bottom 50%
3. Upserts results into a table `ABCReport(warehouse_id, product_id, abc_class, revenue_90d, percent_rank)` — create this table as part of your answer
4. Returns the count of A, B, C items

---

**P043 · Recursive CTE + Aggregation — Supplier Supply Chain Depth**
*Topic: Recursive CTE + aggregation*

Imagine `Suppliers` can have a `parent_supplier_id` (a distributor hierarchy). Add this column to `Suppliers`. Then write a recursive CTE that:
1. Traverses the full supplier tree from any root supplier
2. For each node, aggregates the total purchase order value of all its descendants (including itself)
3. Shows each supplier's `level`, `full_hierarchy_path`, and `total_subtree_po_value`

---

**P044 · Full Pipeline — Customer Lifetime Value**
*Topic: Multi-concept integration*

Build a complete CLV (Customer Lifetime Value) analysis pipeline:

1. **View** `vw_clv_base`: per customer, compute total revenue, order count, first order date, last order date, and average days between orders (use `LAG` on order_date, then `AVG`)
2. **Function** `fn_predicted_clv(p_customer_id INT, p_months INT) RETURNS NUMERIC`: use the customer's avg monthly revenue × predicted months active (assume they'll stay active `p_months` more months if last order < 60 days ago, else 0)
3. **Query** against the view + function to produce a ranked list of top 20 customers by predicted 12-month CLV, annotated with their current `customer_tier` and whether their tier should be upgraded

---

**P045 · Trigger-Driven Integrity + Audit**
*Topic: Triggers + AuditLog*

Write a `BEFORE UPDATE` trigger `trg_prevent_price_decrease` on `Products` that:
1. Raises an exception if `unit_price` is being reduced by more than 30% in a single update
2. If the reduction is between 10–30%, allows it but logs a warning row to `AuditLog` with `operation = 'U'` and `new_data` containing `{"warning": "price_reduction_gt_10pct", "old_price": X, "new_price": Y}`
3. If reduction < 10%, allows silently

Include a test `UPDATE` statement that should trigger the warning log.

---

## Appendix — Sample Data Seed Script (abbreviated)

```sql
-- Categories
INSERT INTO Categories (category_name, parent_category_id) VALUES
  ('Electronics', NULL),
  ('Cables',      1),
  ('Mobile Accessories', 1),
  ('Furniture', NULL),
  ('Office Chairs', 4);

-- Suppliers
INSERT INTO Suppliers (supplier_name, email, payment_terms, rating, country) VALUES
  ('TechSource Pvt Ltd',   'orders@techsource.in',  30, 4.5, 'India'),
  ('GlobalGoods Co',       'supply@globalgoods.com', 45, 3.8, 'China'),
  ('QuickShip Traders',    'info@quickship.in',      15, 4.9, 'India');

-- Warehouses
INSERT INTO Warehouses (warehouse_name, city, state) VALUES
  ('Delhi Central Hub',   'New Delhi',  'Delhi'),
  ('Mumbai West Depot',   'Mumbai',     'Maharashtra'),
  ('Bangalore Tech Store','Bangalore',  'Karnataka');

-- Products (sample)
INSERT INTO Products (sku, product_name, category_id, unit_price, cost_price) VALUES
  ('ELEC-CBL-001', 'USB-C Cable 1m',     2, 299.00,  85.00),
  ('ELEC-CBL-002', 'HDMI Cable 2m',      2, 549.00, 140.00),
  ('ELEC-ACC-001', 'Phone Stand Aluminium', 3, 799.00, 220.00),
  ('FURN-CHR-001', 'Ergonomic Mesh Chair', 5, 12999.00, 5800.00);
```

---

*End of Document — IMS Database Design & SQL Problem Set*
*Total Problems: 45 | Difficulty: Beginner (P001–P010) → Intermediate (P011–P028) → Advanced (P029–P045)*
