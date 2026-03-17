package com.gurpreet.comparator.comparators;

import java.util.Comparator;


import com.gurpreet.comparator.models.Movie;

public class MovieNameYearComparator implements Comparator<Movie> {

	@Override
	public int compare(Movie movie1, Movie movie2) {
		
		if(getYearOfMovie(movie2) != getYearOfMovie(movie1)){
            return Integer.compare(getYearOfMovie(movie2), getYearOfMovie(movie1));   
        }
		
		return getNameOfMovie(movie1).compareTo(getNameOfMovie(movie2));

	}

	private String getNameOfMovie(Movie movie) {
		return movie.getName();
	}
	
	private int getYearOfMovie(Movie movie) {
		return movie.getYear();
	}
}
