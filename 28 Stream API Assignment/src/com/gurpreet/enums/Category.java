package com.gurpreet.enums;

public enum Category {

    ELECTRONICS("Electronics"),
    FASHION("Fashion & Apparel"),
    BOOKS("Books & Literature"),
    HOME_APPLIANCES("Home Appliances"),
    BEAUTY("Beauty & Personal Care"),
    SPORTS("Sports & Outdoors"),
    TOYS("Toys & Games"),
    FURNITURE("Furniture"),
    GROCERY("Grocery & Food Items"),
    MOBILES("Mobiles & Accessories"),
    COMPUTERS("Computers & Laptops");

    private final String displayName;

    // Constructor
    Category(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the user-friendly display name of the category.
     */
    public String getDisplayName() {
        return displayName;
    }

    
    public String getCode() {
        return name();
    }

    @Override
    public String toString() {
        return  displayName;
    }
}