package com.gurpreet.constructor.models;

/**
 * PremiumAccount extends BankAccount
 * Demonstrates:
 * 1. super() to initialize parent
 * 2. Additional initialization in child
 * 3. Constructor chaining in inheritance
 */
public class PremiumAccount extends BankAccount {
    private double bonusAmount;
    private String membershipLevel; // Gold, Platinum, Diamond
    
    /**
     * Primary constructor
     * MUST call super() first to initialize parent
     */
    public PremiumAccount(String accountNumber, String accountHolderName, 
                         double balance, double bonusAmount, String membershipLevel) {
        // MUST call parent constructor first
        // This ensures parent's invariants are enforced
        super(accountNumber, accountHolderName, balance);
        
        // Validate bonus amount
        if (bonusAmount < 0) {
            System.out.println("Bonus amount cannot be negative");
        }
        
        // Validate membership level
        if (membershipLevel == null || membershipLevel.trim().isEmpty()) {
            System.out.println("Membership level cannot be null or empty");
        }
        
        this.bonusAmount = bonusAmount;
        this.membershipLevel = membershipLevel;
        
        // Add bonus to balance after account creation
        if (bonusAmount > 0) {
            deposit(bonusAmount);
            System.out.println("Welcome bonus of ₹" + bonusAmount + " added!");
        }
    }
    
    /**
     * Constructor overloading - default membership level
     */
    public PremiumAccount(String accountNumber, String accountHolderName, 
                         double balance, double bonusAmount) {
        this(accountNumber, accountHolderName, balance, bonusAmount, "Gold");
    }
    
    /**
     * Constructor overloading - default balance and bonus
     */
    public PremiumAccount(String accountNumber, String accountHolderName) {
        this(accountNumber, accountHolderName, 0.0, 500.0, "Gold");
    }
    
    public double getBonusAmount() {
        return bonusAmount;
    }
    
    public String getMembershipLevel() {
        return membershipLevel;
    }
    
    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("Membership Level: " + membershipLevel);
        System.out.println("Welcome Bonus: ₹" + bonusAmount);
    }
}

/**
 * EXPLANATIONS:
 * 
 * Q: Why child constructor must call parent constructor first?
 * A: 1. Parent's state must be initialized before child's state
 *    2. Parent's validation rules must be enforced first
 *    3. Child depends on parent's valid state
 *    4. Java enforces this - super() must be first statement
 * 
 * Q: What happens if super() is not called?
 * A: 1. Compilation error - Java requires it
 *    2. If it were allowed, parent fields would be uninitialized
 *    3. Parent's validation would be skipped
 *    4. Object would be in invalid state
 *    5. Methods inherited from parent would fail
 * 
 * Example of what would break:
 *   - accountNumber would be null
 *   - balance would be 0 (default) without validation
 *   - Business rules would be violated
 */
