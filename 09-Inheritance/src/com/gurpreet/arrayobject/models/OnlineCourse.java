package com.gurpreet.arrayobject.models;

public class OnlineCourse extends Course{
	
	private double totalFees;
	
	public OnlineCourse(String courseName, double baseFee) {
		super(courseName, baseFee);
		super.courseType = "Online";
		
	}
	
	@Override
	public void calculateTotalFee(double techCharges) {
		this.totalFees += super.getBaseFee() + techCharges;
	}
	
	@Override
	public double getTotalFee() {
		return totalFees;
	}

}
