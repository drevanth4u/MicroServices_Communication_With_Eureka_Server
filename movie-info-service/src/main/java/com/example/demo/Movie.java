package com.example.demo;

public class Movie {
	
	private String movieId;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Movie(String movieId, String desc) {
		this.movieId = movieId;
		this.name = desc;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	
	

}
