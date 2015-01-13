package com.twu.biblioteca;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Created by mahalaks on 13/01/15.
 */
public class BookTest {

    @Test
    public void shouldBeAbleToCreateBook()
    {
        Book book = new Book("100", "Angels and Demons", "Dan Brown", "1999", true);
        assertThat(book.getAccessionNo(), Is.is("100"));
        assertThat(book.getName(), Is.is("Angels and Demons"));
        assertThat(book.getAuthor(), Is.is("Dan Brown"));
        assertThat(book.getYearPublished(), Is.is("1999"));
        assertThat(book.getIsAvailable(), Is.is(true));
    }
}
