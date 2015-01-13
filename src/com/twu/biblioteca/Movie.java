package com.twu.biblioteca;

/**
 * Created by mahalaks on 13/01/15.
 */
public class Movie extends Item{

    private String id;
    private String name;
    private String year;
    private String director;
    private int rating;
    private Boolean isAvailable;


    public Movie(String id, String name, String year, String director, int rating, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public String getId() {
        return id;
    }

    public void setIsAvailable(boolean available)
    {
        this.isAvailable = available;
    }
}
