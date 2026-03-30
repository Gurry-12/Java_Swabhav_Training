package com.gurpreet.enums;

public enum Disease {

	CARDIOLOGY("Cardiology - Heart Related"), NEUROLOGY("Neurology - Brain & Nervous System"),
	ORTHOPEDICS("Orthopedics - Bones & Joints"), PEDIATRICS("Pediatrics - Child Health"),
	DIABETOLOGY("Diabetology - Diabetes"), PULMONOLOGY("Pulmonology - Respiratory Diseases"),
	GASTROENTEROLOGY("Gastroenterology - Digestive System"), DERMATOLOGY("Dermatology - Skin Diseases"),
	OPHTHALMOLOGY("Ophthalmology - Eye Diseases"), PSYCHIATRY("Psychiatry - Mental Health"),
	GENERAL_MEDICINE("General Medicine"), ASTHMA("Asthma"), MIGRAINE("Migraine & Headache");

	private final String displayName;

	// Constructor
	Disease(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Returns the user-friendly display name of the disease.
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Returns the enum constant name (useful for internal use and grouping).
	 */
	public String getCode() {
		return name();
	}

	@Override
	public String toString() {
		return displayName;
	}
}