package com.gurpreet.helpers;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * ValidationHelper - Advanced validation with regex patterns
 * For email, mobile number, and name validation
 */
public class ValidationHelper {
    
    // Regex patterns
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );
    
    private static final Pattern MOBILE_PATTERN = Pattern.compile(
        "^[6-9]\\d{9}$"  // Indian mobile number: starts with 6-9, total 10 digits
    );
    
    private static final Pattern NAME_PATTERN = Pattern.compile(
        "^[a-zA-Z]+(\\s[a-zA-Z]+)*$"  // Only letters and spaces, no numbers or special chars
    );
    
    private static final Pattern DEVICE_ID_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9_-]+$"  // Alphanumeric with underscore and hyphen
    );
    
    /**
     * Validate and get email address
     * Format: username@domain.com
     */
    public static String validateEmail(Scanner scanner) {
        String email;
        while (true) {
            email = scanner.nextLine().trim();
            
            if (email.isEmpty()) {
                System.out.print("Email cannot be empty. Enter again: ");
                continue;
            }
            
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                System.out.print("Invalid email format. Enter valid email (e.g., user@example.com): ");
                continue;
            }
            
            break;
        }
        return email;
    }
    
    /**
     * Validate and get mobile number
     * Format: 10 digits starting with 6-9 (Indian format)
     */
    public static String validateMobileNumber(Scanner scanner) {
        String mobile;
        while (true) {
            mobile = scanner.nextLine().trim();
            
            if (mobile.isEmpty()) {
                System.out.print("Mobile number cannot be empty. Enter again: ");
                continue;
            }
            
            if (!MOBILE_PATTERN.matcher(mobile).matches()) {
                System.out.print("Invalid mobile number. Enter 10 digits starting with 6-9: ");
                continue;
            }
            
            break;
        }
        return mobile;
    }
    
    /**
     * Validate and get name
     * Format: Only letters and spaces, no numbers or special characters
     */
    public static String validateName(Scanner scanner) {
        String name;
        while (true) {
            name = scanner.nextLine().trim();
            
            if (name.isEmpty()) {
                System.out.print("Name cannot be empty. Enter again: ");
                continue;
            }
            
            if (!NAME_PATTERN.matcher(name).matches()) {
                System.out.print("Invalid name. Use only letters and spaces: ");
                continue;
            }
            
            if (name.length() < 2) {
                System.out.print("Name too short. Enter at least 2 characters: ");
                continue;
            }
            
            break;
        }
        return name;
    }
    
    /**
     * Validate device ID for push notifications
     * Format: Alphanumeric with underscore and hyphen
     */
    public static String validateDeviceId(Scanner scanner) {
        String deviceId;
        while (true) {
            deviceId = scanner.nextLine().trim();
            
            if (deviceId.isEmpty()) {
                System.out.print("Device ID cannot be empty. Enter again: ");
                continue;
            }
            
            if (!DEVICE_ID_PATTERN.matcher(deviceId).matches()) {
                System.out.print("Invalid device ID. Use alphanumeric, underscore, or hyphen: ");
                continue;
            }
            
            break;
        }
        return deviceId;
    }
    
    /**
     * Check if email is valid (without prompting)
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * Check if mobile number is valid (without prompting)
     */
    public static boolean isValidMobileNumber(String mobile) {
        if (mobile == null || mobile.isEmpty()) {
            return false;
        }
        return MOBILE_PATTERN.matcher(mobile).matches();
    }
    
    /**
     * Check if name is valid (without prompting)
     */
    public static boolean isValidName(String name) {
        if (name == null || name.isEmpty() || name.length() < 2) {
            return false;
        }
        return NAME_PATTERN.matcher(name).matches();
    }
    
    /**
     * Validate message (not empty, reasonable length)
     */
    public static String validateMessage(Scanner scanner) {
        String message;
        while (true) {
            message = scanner.nextLine().trim();
            
            if (message.isEmpty()) {
                System.out.print("Message cannot be empty. Enter again: ");
                continue;
            }
            
            if (message.length() < 5) {
                System.out.print("Message too short. Enter at least 5 characters: ");
                continue;
            }
            
            if (message.length() > 500) {
                System.out.print("Message too long. Maximum 500 characters: ");
                continue;
            }
            
            break;
        }
        return message;
    }
    
    /**
     * Display validation rules for email
     */
    public static void displayEmailRules() {
        System.out.println("Email format: username@domain.com");
        System.out.println("  - Must contain @ symbol");
        System.out.println("  - Domain must have at least one dot");
        System.out.println("  - Valid characters: letters, numbers, +_&*-.");
    }
    
    /**
     * Display validation rules for mobile number
     */
    public static void displayMobileRules() {
        System.out.println("Mobile number format: 10 digits");
        System.out.println("  - Must start with 6, 7, 8, or 9");
        System.out.println("  - Total 10 digits");
        System.out.println("  - Example: 9876543210");
    }
    
    /**
     * Display validation rules for name
     */
    public static void displayNameRules() {
        System.out.println("Name format:");
        System.out.println("  - Only letters and spaces allowed");
        System.out.println("  - No numbers or special characters");
        System.out.println("  - Minimum 2 characters");
        System.out.println("  - Example: John Doe");
    }
}
