package com.twu.biblioteca;

import org.hamcrest.core.Is;
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
    private ByteArrayInputStream inContent = new ByteArrayInputStream("My string".getBytes());
    private PrintStream printContent = new PrintStream(outContent);

    String welcomeMessage = "Welcome";
    String successfulCheckoutMessage = "Thank you! Enjoy";
    String unsuccessfulCheckoutMessage = "That item is not available.";
    String successfulReturnMessage = "Thank you for returning";
    String unsuccessfulReturnMessage = "That is not a valid item to return.";
    String loginSuccessfulMessage = "You are loggedin";
    String logoutSuccessfulMessage = "You are loggedout";

    ArrayList<Book> books = new ArrayList<Book>();
    ArrayList<Movie> movies = new ArrayList<Movie>();
    ArrayList<User> users = new ArrayList<User>();
    BibliotecaInterface bibliotecaInterface;
    Library publicLibrary;
    Menu menu;

    @Before
    public void setUp() {
        System.setOut(printContent);
        inContent.reset();
        System.setIn(inContent);

        menu = new Menu();

        menu.options.add("1.List Books");
        menu.options.add("2.Checkout Book");
        menu.options.add("3.Return Book");
        menu.options.add("4.List Movies");

        books.add(new Book("100", "Angels and Demons", "Dan Brown", "1999", true));
        books.add(new Book("101", "Davinci Code", "Michael", "1990", false));
        books.add(new Book("103", "harry potter", "carry Lounge", "1999", true));

        movies.add(new Movie("104", "Angels and Demons", "1999", "Dan Brown", 5, true));
        movies.add(new Movie("105", "Davinci Code", "1990", "Michael", 4, false));
        movies.add(new Movie("106", "harry potter", "1999", "carry Lounge", 3, true));

        users.add(new User(new Credential("123-1234", "1009"), "maha", "mahalakshme@gmail.com", "9600252894"));
        users.add(new User(new Credential("123-1235", "1006"), "mawa", "mawalakshme@gmail.com", "9600262894"));
        users.add(new User(new Credential("123-1236", "1007"), "mara", "maralakshme@gmail.com", "9600272894"));
        users.add(new Librarian(new Credential("111-1111", "1000"), "king", "king@gmail.com", "9791213456"));

        publicLibrary = new Library(books, movies, users);
        bibliotecaInterface = new BibliotecaInterface(menu, publicLibrary);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void shouldWelcome() {
        byte[] option = "q".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent, printContent, bibliotecaInterface);

        assertThat(outContent.toString(), containsString(welcomeMessage));
    }

    @Test
    public void shouldDisplayMenuAfterBibliotecaIsInitialized() {
        byte[] option = "q".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent, printContent, bibliotecaInterface);

        for (String menuOption : menu.options) {
            assertThat(outContent.toString(), containsString(menuOption));
        }
    }

    @Test
    public void shouldBeAbleToListBooks() {
        byte[] option = "1 N".getBytes();
        inContent = new ByteArrayInputStream(option);

        outContent.reset();

        new Biblioteca(inContent, printContent, bibliotecaInterface);

        for (Book book : books) {
            assertThat(outContent.toString(), containsString(book.getAccessionNo()));
            assertThat(outContent.toString(), containsString(book.getName()));
            assertThat(outContent.toString(), containsString(book.getAuthor()));
            assertThat(outContent.toString(), containsString(book.getYearPublished()));
        }
    }

    @Test
    public void ValidationOfUserCredentials() {
        boolean isValid = bibliotecaInterface.AreUserCredentialsValid(new Credential("123-1234", "1009"));
        assertThat(isValid, Is.is(true));
    }

    @Test
    public void ValidationOfUserCredentialsWhenPasswordIsIncorrect() {
        boolean isValid = bibliotecaInterface.AreUserCredentialsValid(new Credential("123-1234", "1000"));
        assertThat(isValid, Is.is(false));
    }

    @Test
    public void ValidationOfInvalidUserCredentials() {
        boolean isValid = bibliotecaInterface.AreUserCredentialsValid(new Credential("123-1239", "1000"));
        assertThat(isValid, Is.is(false));
    }

    @Test
    public void shouldBeAbleToCheckoutBook() {
        bibliotecaInterface.AreUserCredentialsValid(new Credential("123-1234", "1009"));
        String message = bibliotecaInterface.CheckoutBook("100");
        assertThat(message, Is.is(successfulCheckoutMessage));
    }

    @Test
    public void checkIfCheckoutOfBookThatDoesNotBelongToLibraryIsUnsuccessful() {
        String message = bibliotecaInterface.CheckoutBook("109");
        assertThat(message, Is.is(unsuccessfulCheckoutMessage));
    }

    @Test
    public void checkIfCheckoutOfUnavailableBookIsUnsuccessful() {
        String message = bibliotecaInterface.CheckoutBook("101");
        assertThat(message, Is.is(unsuccessfulCheckoutMessage));
    }

    @Test
    public void shouldBeAbleToReturnCheckedoutBook() {
        bibliotecaInterface.AreUserCredentialsValid(new Credential("123-1234", "1009"));
        bibliotecaInterface.CheckoutBook("100");
        String message = bibliotecaInterface.ReturnBook("100");
        assertThat(message, Is.is(successfulReturnMessage));
    }

    @Test
    public void shouldFailWhenTriedToReturnBookThatWasNotCheckedout() {
        bibliotecaInterface.AreUserCredentialsValid(new Credential("123-1234", "1009"));
        String message = bibliotecaInterface.ReturnBook("100");
        assertThat(message, Is.is(unsuccessfulReturnMessage));
    }

    @Test
    public void shouldFailWhenTriedToReturnBookThatWasCheckedoutByDifferentUser() {
        bibliotecaInterface.AreUserCredentialsValid(new Credential("123-1234", "1009"));
        String message = bibliotecaInterface.ReturnBook("101");
        assertThat(message, Is.is(unsuccessfulReturnMessage));
    }

    @Test
    public void shouldFailWhenTriedToReturnBookThatDoesNotBelongToLibrary() {
        bibliotecaInterface.AreUserCredentialsValid(new Credential("123-1234", "1009"));
        String message = bibliotecaInterface.ReturnBook("109");
        assertThat(message, Is.is(unsuccessfulReturnMessage));
    }

    @Test
    public void shouldBeAbleToCheckoutMovie() {
        bibliotecaInterface.AreUserCredentialsValid(new Credential("123-1234", "1009"));
        String message = bibliotecaInterface.CheckoutMovie("104");
        assertThat(message, Is.is(successfulCheckoutMessage));
    }

    @Test
    public void checkIfCheckoutOfMovieThatDoesNotBelongToLibraryIsUnsuccessful() {
        String message = bibliotecaInterface.CheckoutMovie("109");
        assertThat(message, Is.is(unsuccessfulCheckoutMessage));
    }

    @Test
    public void checkIfCheckoutOfUnavailableMovieIsUnsuccessful() {
        String message = bibliotecaInterface.CheckoutMovie("105");
        assertThat(message, Is.is(unsuccessfulCheckoutMessage));
    }

    @Test
    public void checkIfLoggedinUserCanSeeAdditionalMenuOptionForSeeingHisInfo()
    {
        byte[] option = "2 123-1234 1009 100 Y 1 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent, printContent, bibliotecaInterface);
        assertThat(outContent.toString(), containsString("6.Display my info"));
    }

    @Test
    public void checkIfNotLoggedinUserCannotSeeAdditionalMenuOptionForSeeingHisInfo()
    {
        byte[] option = "1 123-1234 1009 100 Y 4 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent, printContent, bibliotecaInterface);
        assertTrue(!outContent.toString().contains("6.Display my info"));
    }

    @Test
    public void checkIfLoggedinUserCanSeeHisInfo() {
        byte[] option = "2 123-1234 1009 100 Y 6 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent, printContent, bibliotecaInterface);

        assertThat(outContent.toString(), containsString(users.get(0).getName()));
        assertThat(outContent.toString(), containsString(users.get(0).getEmailId()));
        assertThat(outContent.toString(), containsString(users.get(0).getPhoneNo()));
    }

    @Test
    public void checkLogin()
    {
        byte[] option = "9 123-1234 1009 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent, printContent, bibliotecaInterface);
        assertThat(outContent.toString(), containsString(loginSuccessfulMessage));
    }

    @Test
    public void checkLogout()
    {
        byte[] option = "9 123-1234 1009 Y 8 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent, printContent, bibliotecaInterface);
        assertThat(outContent.toString(), containsString(logoutSuccessfulMessage));
    }

    @Test
    public void checkIfLibrarianCanFindWhoHasCheckedoutBook()
    {
        byte[] option = "2 123-1234 1009 100 Y 8 Y 9 111-1111 1000 Y 7 100 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent, printContent, bibliotecaInterface);
        assertThat(outContent.toString(), containsString("name:" + users.get(0).getName()));
        assertThat(outContent.toString(), containsString("email id:" + users.get(0).getEmailId()));
        assertThat(outContent.toString(), containsString("phone no:" + users.get(0).getPhoneNo()));
    }

    @Test
    public void shouldBeAbleToListMovies() {
        byte[] option = "4 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent, printContent, bibliotecaInterface);

        for (Movie movie : movies) {
            if (movie.getIsAvailable()) {
                assertThat(outContent.toString(), containsString(movie.getName()));
            } else {
                assertTrue(!outContent.toString().contains("Davinci Code"));
            }
        }
    }
}

