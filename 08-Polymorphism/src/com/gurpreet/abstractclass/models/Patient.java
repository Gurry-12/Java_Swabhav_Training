package com.gurpreet.abstractclass.models;


public abstract class Patient {
	private long counter = 1000L;
    private String patientId;
    private String name;

    public Patient( String name) {
        this.patientId = "P" + ++counter;
        this.name = name;
    }

    public abstract double calculateCharges();

    public void generateBill() {
        double charges = calculateCharges();
        double tax = charges * 0.10;  // 10% tax, common to all
        double finalAmount = charges + tax;
        System.out.println("-------------------------");
        System.out.println("         Bill            ");
        System.out.println("ID: " + patientId );
        System.out.println("Patient " + name );
        System.out.println("Charges = " + charges );
        System.out.println("Tax = " + tax );
        System.out.println("Final Amount = " + finalAmount);
    }
}

