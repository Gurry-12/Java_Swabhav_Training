package com.gurpreet.arrayobject.models;

public class RegularCourse extends Course {

		private double totalFees;

	public RegularCourse(String courseName, double baseFee) {
		super(courseName, baseFee);
		super.courseType = "Regular";
	}
	
	@Override
	public void calculateTotalFee( double additionalCharges, double labCharges) {
		this.totalFees  += super.getBaseFee() + additionalCharges + labCharges;
	}
	
	@Override
	public double getTotalFee() {
		return totalFees;
	}

}
