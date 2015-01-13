package com.twu.biblioteca;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Created by mahalaks on 13/01/15.
 */
public class MovieTest {

    @Test
    public void shouldBeAbleToCreateMovie()
    {
        Movie movie = new Movie("101", "Angels and Demons", "1999", "Dan Brown", 4, true);
        assertThat(movie.getName(), Is.is("Angels and Demons"));
        assertThat(movie.getYear(), Is.is("1999"));
        assertThat(movie.getDirector(), Is.is("Dan Brown"));
        assertThat(movie.getRating(), Is.is(4));
        assertThat(movie.getIsAvailable(), Is.is(true));
    }
}
