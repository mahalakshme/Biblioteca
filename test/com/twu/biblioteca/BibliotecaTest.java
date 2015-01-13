package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Created by mahalaks on 10/01/15.
 */
public class BibliotecaTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent = new ByteArrayInputStream("My string".getBytes());

    String welcomeMessage = "Welcome";
    String successfulCheckoutMessage = "Thank you! Enjoy";
    String unsuccessfulCheckoutMessage = "That item is not available.";
    String successfulReturnMessage = "Thank you for returning";
    String unsuccessfulReturnMessage = "That is not a valid item to return.";

    ArrayList<Book> books = new ArrayList<Book>();
    ArrayList<Movie> movies = new ArrayList<Movie>();


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        inContent.reset();
        System.setIn(inContent);
    }

    @Before
    public void CreateBookList()
    {
        books.add(new Book("100", "Angels and Demons", "Dan Brown", "1999", true));
        books.add(new Book("101", "Davinci Code", "Michael", "1990", false));
        books.add(new Book("103", "harry potter", "carry Lounge", "1999", true));
    }

    @Before
    public void CreateMovieList()
    {
        movies.add(new Movie("100", "Angels and Demons", "1999", "Dan Brown", 5, true));
        movies.add(new Movie("101", "Davinci Code", "1990", "Michael", 4, false));
        movies.add(new Movie("102", "harry potter", "1999", "carry Lounge", 3, true));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void shouldWelcome()
    {
        byte[] option = "q".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca();

        assertThat(outContent.toString(), containsString(welcomeMessage));

    }

    @Test
    public void shouldBeAbleToListBooks()
    {
        byte[] option = "1 N".getBytes();
        inContent = new ByteArrayInputStream(option);

        outContent.reset();

        new Biblioteca();

        for (Book book : books) {
            assertThat(outContent.toString(), containsString(book.getAccessionNo()));
            assertThat(outContent.toString(), containsString(book.getName()));
            assertThat(outContent.toString(), containsString(book.getAuthor()));
            assertThat(outContent.toString(), containsString(book.getYearPublished()));
        }
    }

    @Test
    public void shouldBeAbleToCheckoutBook()
    {
        byte[] option = "2 100 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca();
        assertThat(outContent.toString(), containsString(successfulCheckoutMessage));
    }

    @Test
    public void checkIfInvalidCheckoutOfBookIsUnsuccessful()
    {
        byte[] option = "2 109 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca();
        assertThat(outContent.toString(), containsString(unsuccessfulCheckoutMessage));
    }

    @Test
    public void checkIfCheckoutOfUnavailableBookUnsuccessful()
    {
        byte[] option = "2 101 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca();
        assertThat(outContent.toString(), containsString(unsuccessfulCheckoutMessage));
    }

    @Test
    public void shouldBeAbleToReturn()
    {
        byte[] option = "3 101 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca();
        assertThat(outContent.toString(), containsString(successfulReturnMessage));
    }

    @Test
    public void checkIfInvalidReturnIsUnsuccessful()
    {
        byte[] option = "3 109 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca();
        assertThat(outContent.toString(), containsString(unsuccessfulReturnMessage));
    }

    @Test
    public void checkIfReturnOfExistingBookUnsuccessful()
    {
        byte[] option = "3 103 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca();
        assertThat(outContent.toString(), containsString(unsuccessfulReturnMessage));
    }

    @Test
    public void checkIfAbleToContinueChoosingOptions()
    {
        byte[] option = "1 Y 2 100 Y 3 100 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca();

        for (Book book : books) {
            assertThat(outContent.toString(), containsString(book.getAccessionNo()));
            assertThat(outContent.toString(), containsString(book.getName()));
            assertThat(outContent.toString(), containsString(book.getAuthor()));
            assertThat(outContent.toString(), containsString(book.getYearPublished()));
        }

        assertThat(outContent.toString(), containsString(successfulCheckoutMessage));
        assertThat(outContent.toString(), containsString(successfulReturnMessage));
    }

    @Test
    public void shouldBeAbleToListMovies()
    {
        byte[] option = "4 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);


        for (Movie movie : movies) {
            if(movie.getIsAvailable()) {
                assertThat(outContent.toString(), containsString(movie.getName()));
            }
            else
            {
                assertTrue(!outContent.toString().contains("Davinci Code"));
            }
        }
    }

    @Test
    public void shouldBeAbleToCheckoutMovie()
    {
        byte[] option = "5 106 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);
        assertThat(outContent.toString(), containsString(successfulCheckoutMessage));
    }

    @Test
    public void checkIfCheckoutOfUnavailableMovieIsUnsuccessful()
    {
        byte[] option = "5 105 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);
        assertThat(outContent.toString(), containsString(unsuccessfulCheckoutMessage));
    }

    @Test
    public void checkIfInvalidCheckoutOfMovieIsUnsuccessful()
    {
        byte[] option = "5 109 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);
        assertThat(outContent.toString(), containsString(unsuccessfulCheckoutMessage));
    }


}
