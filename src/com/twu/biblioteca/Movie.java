package com.twu.biblioteca;

/**
 * Created by mahalaks on 13/01/15.
 */
public class Movie extends Item{

    private String year;
    private String director;
    private int rating;

    public Movie(String id, String name, String year, String director, int rating, boolean isAvailable) {
        this.accessionNo = id;
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.isAvailable = isAvailable;
    }

    String getYear() {
        return year;
    }

    String getDirector() {
        return director;
    }

    int getRating() {
        return rating;
    }
}
