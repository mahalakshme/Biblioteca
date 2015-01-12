package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by mahalaks on 10/01/15.
 */
public class BibliotecaTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent = new ByteArrayInputStream("My string".getBytes());

    String welcomeMessage = "Welcome";
    String successfulCheckoutMessage = "Thank you! Enjoy the book";
    String unsuccessfulCheckoutMessage = "That book is not available.";
    String successfulReturnMessage = "Thank you for returning the book.";
    String unsuccessfulReturnMessage = "That is not a valid book to return.";

    ArrayList<String> menu = new ArrayList<String>();
    ArrayList<Book> books = new ArrayList<Book>();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        inContent.reset();
        System.setIn(inContent);
    }

    @Before
    public void CreateMenu()
    {
        menu.add("1.List Books");
        menu.add("2.Checkout Book");
    }

    @Before
    public void CreateBookList()
    {
       books.add(new Book("100", "Angels and Demons", "Dan Brown", "1999", true));
       books.add(new Book("101", "Davinci Code", "Michael", "1990", false));
        books.add(new Book("103", "harry potter", "carry Lounge", "1999", true));
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

        new Biblioteca(inContent);

        assertThat(outContent.toString(), containsString(welcomeMessage));

    }

    @Test
    public void shouldListMenu()
    {
        byte[] option = "q".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);

        for (String entry : menu) {
            assertThat(outContent.toString(), containsString(entry));
        }
    }

    @Test
    public void shouldBeAbleToListBooks()
    {
        byte[] option = "1 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);

        for (Book book : books) {
            assertThat(outContent.toString(), containsString(book.accessionNo));
            assertThat(outContent.toString(), containsString(book.name));
            assertThat(outContent.toString(), containsString(book.author));
            assertThat(outContent.toString(), containsString(book.yearPublished));
        }
    }

    @Test
    public void shouldBeAbleToCheckoutBook()
    {
        byte[] option = "2 100 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);
        assertThat(outContent.toString(), containsString(successfulCheckoutMessage));
    }

    @Test
    public void checkIfInvalidCheckoutIsUnsuccessful()
    {
        byte[] option = "2 109 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);
        assertThat(outContent.toString(), containsString(unsuccessfulCheckoutMessage));
    }

    @Test
    public void checkIfCheckoutOfUnavailableBookUnsuccessful()
    {
        byte[] option = "2 101 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);
        assertThat(outContent.toString(), containsString(unsuccessfulCheckoutMessage));
    }

    @Test
    public void shouldBeAbleToReturn()
    {
        byte[] option = "3 101 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);
        assertThat(outContent.toString(), containsString(successfulReturnMessage));
    }

    @Test
    public void checkIfInvalidReturnIsUnsuccessful()
    {
        byte[] option = "3 109 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);
        assertThat(outContent.toString(), containsString(unsuccessfulReturnMessage));
    }

    @Test
    public void checkIfReturnOfExistingBookUnsuccessful()
    {
        byte[] option = "3 103 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);
        assertThat(outContent.toString(), containsString(unsuccessfulReturnMessage));
    }

    @Test
    public void checkIfAbleToContinueChoosingOptions()
    {
        byte[] option = "1 Y 2 100 Y 3 100 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);

        for (Book book : books) {
            assertThat(outContent.toString(), containsString(book.accessionNo));
            assertThat(outContent.toString(), containsString(book.name));
            assertThat(outContent.toString(), containsString(book.author));
            assertThat(outContent.toString(), containsString(book.yearPublished));
        }

        assertThat(outContent.toString(), containsString(successfulCheckoutMessage));
        assertThat(outContent.toString(), containsString(successfulReturnMessage));
    }
}
