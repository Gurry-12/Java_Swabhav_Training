package com.gurpreet.constructor.models;

/**
 * DiscountedOrder extends Order
 * Demonstrates:
 * 1. Parent must calculate base total first
 * 2. Child then applies discount
 * 3. Proper initialization order
 */
public class DiscountedOrder extends Order {
    private double discountPercent;
    private double discountAmount;
    private double finalAmount;
    
    /**
     * Primary constructor
     * MUST call super() first to calculate base total
     */
    public DiscountedOrder(Product product, int quantity, double discountPercent) {
        // Call parent constructor FIRST
        // This calculates base total amount
        super(product, quantity);
        
        // Validate discount percentage
        if (discountPercent < 0 || discountPercent > 100) {
            System.out.println(
                "Discount percent must be between 0 and 100. Provided: " + discountPercent
            );
            return;
        }
        
        this.discountPercent = discountPercent;
        
        // Calculate discount based on parent's total
        this.discountAmount = getTotalAmount() * (discountPercent / 100);
        
        // Calculate final amount after discount
        this.finalAmount = getTotalAmount() - discountAmount;
        
        System.out.println("Discount applied: " + discountPercent + "% (₹" + discountAmount + ")");
        System.out.println("Final amount: ₹" + finalAmount);
    }
    
    /**
     * Constructor overloading - default discount
     */
    public DiscountedOrder(Product product, int quantity) {
        this(product, quantity, 10.0); // Default 10% discount
    }
    
    public double getDiscountPercent() {
        return discountPercent;
    }
    
    public double getDiscountAmount() {
        return discountAmount;
    }
    
    public double getFinalAmount() {
        return finalAmount;
    }
    
    @Override
    public void displayOrderInfo() {
        super.displayOrderInfo();
        System.out.println("Discount: " + discountPercent + "%");
        System.out.println("Discount Amount: ₹" + discountAmount);
        System.out.println("Final Amount: ₹" + finalAmount);
    }
}

/**
 * EXPLANATIONS:
 * 
 * Q: Why parent initialization should not be skipped?
 * A: 1. Parent calculates base total amount
 *    2. Child's discount calculation depends on parent's total
 *    3. Parent validates product and quantity
 *    4. Parent reduces stock from inventory
 *    5. Parent generates order ID
 *    6. Without parent init, child has no base to work with
 * 
 * Example of what breaks without super():
 *   DiscountedOrder order = new DiscountedOrder(product, 5, 20);
 *   // Without super():
 *   order.getTotalAmount();    // Returns 0 or undefined
 *   order.getOrderId();        // Returns 0 or undefined
 *   order.getProduct();        // Returns null
 *   order.getDiscountAmount(); // Calculated on 0 = wrong!
 *   order.getFinalAmount();    // Wrong calculation
 * 
 * Q: Why constructor is better than setter in this scenario?
 * A: 1. Order is immutable transaction - cannot change after creation
 *    2. All data (product, quantity, discount) known at creation
 *    3. Calculations must be done atomically
 *    4. Cannot have "partial order" state
 *    5. Business rule: order is placed with all details at once
 *    6. Prevents inconsistency (changing discount after order placed)
 * 
 * Correct initialization order:
 *   1. super() calls Order constructor
 *   2. Order validates product and quantity
 *   3. Order calculates base total
 *   4. Order reduces stock
 *   5. Control returns to DiscountedOrder
 *   6. DiscountedOrder validates discount
 *   7. DiscountedOrder calculates discount amount
 *   8. DiscountedOrder calculates final amount
 *   9. Object is fully initialized and valid
 */
