package com.gurpreet.model;

public class StringUtility {

	public boolean isEmpty(String s) {
		return s == null || s.isEmpty();
	}

	public String toUpperCase(String s) {
		if (s == null)
			return null;
		return s.toUpperCase();
	}

	public int getLength(String s) {
		if (s == null)
			return 0;
		return s.length();
	}
}
