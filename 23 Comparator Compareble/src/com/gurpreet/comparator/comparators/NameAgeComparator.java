package com.gurpreet.comparator.comparators;

import java.util.Comparator;
import com.gurpreet.comparator.models.Candidate;
import com.gurpreet.comparator.models.Movie;

public class NameAgeComparator implements Comparator<Candidate> {

	@Override
	public int compare(Candidate candidate1, Candidate candidate2) {

		int nameResult = getNameOfCandidate(candidate1).compareTo(getNameOfCandidate(candidate1));

		if (nameResult != 0)
			return nameResult;

		return getAgeOfCandidate(candidate1) - getAgeOfCandidate(candidate2);
	}

	private String getNameOfCandidate(Candidate candidate) {
		return candidate.getName();
	}

	private int getAgeOfCandidate(Candidate candidate) {
		return candidate.getAge();
	}

}
