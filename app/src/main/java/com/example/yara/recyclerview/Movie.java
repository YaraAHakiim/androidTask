package com.example.yara.recyclerview;

/**
 * Created by YaRa on 12/27/2016.
 */

public class Movie {
    private String title , genre , year ;

    public Movie()
    {}

    public Movie(String title, String genre , String year)
    {
        this.title = title ;
        this.genre = genre ;
        this.year = year ;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
