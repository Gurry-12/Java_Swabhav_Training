package com.gurpreet.enums;

public enum Genre {

	FICTION("Fiction"), NON_FICTION("Non-Fiction"), SCIENCE_FICTION("Science Fiction"), FANTASY("Fantasy"),
	MYSTERY("Mystery & Thriller"), ROMANCE("Romance"), HORROR("Horror"), BIOGRAPHY("Biography & Autobiography"),
	HISTORY("History");

	private final String displayName;

	// Constructor
	Genre(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Returns the user-friendly display name of the genre.
	 */
	public String getDisplayName() {
		return displayName;
	}


	public String getCode() {
		return name();
	}

	@Override
	public String toString() {
		return displayName;
	}
}