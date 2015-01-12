package com.twu.biblioteca;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static org.junit.Assert.assertThat;


/**
 * Created by mahalaks on 10/01/15.
 */
public class BibliotecaTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent = new ByteArrayInputStream("My string".getBytes());

    String welcomeMessage = "Welcome";
    String invalidOption = "Select a valid option!";
    String continueMessage = "Do you want to continue?<Y/N>";
    String quitMessage = "Enter q to Quit";
    String successfulCheckoutMessage = "Thank you! Enjoy the book";
    String unsuccessfulCheckoutMessage = "That book is not available.";

    String booksAvailableFile = "books available";
    String bookStoreFile = "books";

    ArrayList<String> menu = new ArrayList<String>();

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

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void shouldWelcomeAndListOptions()
    {

        byte[] option = "q".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);

        ArrayList<String> welcomeOptionsTest = new ArrayList<String>();
        GenerateListFromOutputStream(welcomeOptionsTest);
        ArrayList<String> welcomeOptions = new ArrayList<String>();

        AddBasicMessagesToList(welcomeOptions);

        assertThat(welcomeOptionsTest, Is.is(welcomeOptions));
    }

     @Test
    public void shouldBeAbleToChooseOption() throws IOException {
         byte[] option = "1 N".getBytes();
         inContent = new ByteArrayInputStream(option);
         outContent.reset();

        new Biblioteca(inContent);

        ArrayList<String> books = new ArrayList<String>();
        ArrayList<String> bookTest = new ArrayList<String>();

         AddBasicMessagesToList(books);


         shouldCheckListBooksHelper(books, bookTest);
         books.add(continueMessage);
         assertThat(bookTest, Is.is(books));

    }

    @Test
    public void shouldCheckForInvalidOption()
    {
        byte[] option = "9 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);
        ArrayList<String> expected = new ArrayList<String>();
        ArrayList<String> actual = new ArrayList<String>();

        AddBasicMessagesToList(expected);
        expected.add(invalidOption);
        expected.add(continueMessage);

        GenerateListFromOutputStream(actual);

        assertThat(actual, Is.is(expected));
    }

    @Test
    public void shouldBeAbleToContinueChoosingOptionsUntilQuit()
    {
        byte[] option = "1 Y 1 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);

        ArrayList<String> books = new ArrayList<String>();
        ArrayList<String> bookTest = new ArrayList<String>();
        ArrayList<String> expected = new ArrayList<String>();

        AddBasicMessagesToList(expected);

        shouldCheckListBooksHelper(books, bookTest);

        expected.addAll(books);
        expected.add(continueMessage);
        expected.addAll(menu);
        expected.add(quitMessage);
        expected.addAll(books);
        expected.add(continueMessage);

        assertThat(bookTest, Is.is(expected));
    }

    @Test
    public void shouldBeAbleToCheckoutBook()
    {
        byte[] option = "2 101 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);

        ArrayList<String> bookTest = new ArrayList<String>();
        ReadFile(bookTest);
        boolean isContains = false;

        for (String bookDetails : bookTest) {
            StringTokenizer book = new StringTokenizer(bookDetails, ":");
            if(book.nextToken().equals("101"))
            {
                isContains = true;
                break;
            }
        }

        assertThat(isContains, Is.is(false));
    }

    @Test
    public void shouldDisplayMessageAfterSuccessfulCheckout()
    {
        byte[] option = "2 100 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);
        String messages[] = outContent.toString().split("\n");
        String actualCheckoutMesssage = messages[messages.length - 1];
        assertThat(actualCheckoutMesssage, Is.is(successfulCheckoutMessage));
    }

    @Test
    public void shouldDisplayMessageCheckoutUnsuccessful()
    {
        byte[] option = "2 105 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);
        String messages[] = outContent.toString().split("\n");
        String actualCheckoutMesssage = messages[messages.length - 1];
        assertThat(actualCheckoutMesssage, Is.is(unsuccessfulCheckoutMessage));
    }

    @Test
    public void shouldBeAbleToReturn()
    {
        byte[] option = "3 106 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);

        ArrayList<String> bookTest = new ArrayList<String>();
        ReadFile(bookTest);
        boolean isContains = false;

        for (String bookDetails : bookTest) {
            StringTokenizer book = new StringTokenizer(bookDetails, ":");
            if(book.nextToken().equals("106"))
            {
                isContains = true;
                break;
            }
        }

        assertThat(isContains, Is.is(true));
    }

    private void AddBasicMessagesToList(ArrayList<String> expected) {
        expected.add(welcomeMessage);
        expected.addAll(menu);
        expected.add(quitMessage);
    }

    private void shouldCheckListBooksHelper(ArrayList<String> books, ArrayList<String> bookTest) {
        ReadFile(books);


        StringTokenizer bookList = new StringTokenizer(outContent.toString(), "\n");

        while (bookList.hasMoreElements()) {
            String book = bookList.nextToken();
            bookTest.add(book);
        }
    }

    private void ReadFile(ArrayList bookTest)
    {


        String line;

        try {
            FileReader fileReader =
                    new FileReader(booksAvailableFile);

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                bookTest.add(line);
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            booksAvailableFile + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + booksAvailableFile + "'");
        }
    }

    private void GenerateListFromOutputStream(ArrayList<String> list) {
        StringTokenizer option = new StringTokenizer(outContent.toString(), "\n");


        while (option.hasMoreElements()) {

            String opt = option.nextToken();
            list.add(opt);
        }
    }



  /*  private ArrayList<String> GetMenuOutputStream(Biblioteca sample) {

       // outContent.reset();
       // sample.DisplayMenu();
        ArrayList<String> menuTest = new ArrayList<String>();
        GenerateListFromOutputStream(menuTest);
        return menuTest;
    }







    //@Test
    public void shouldDisplayMenu() throws IOException {
        Biblioteca sample= new Biblioteca();


        ArrayList<String> menuTest = GetMenuOutputStream(sample);

        assertThat(menuTest, Is.is(menu));
    }

    // @Test
    public void shouldBeAbleToAddOptionsToMenu() throws IOException {

        byte[] option = "0".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        Biblioteca sample = new Biblioteca(inContent, outContent);

        sample.AddOptionsToMenu("2.Checkin book");

        ArrayList<String> menuOptions = new ArrayList<String>();
        menuOptions.add(welcomeMessage);
        menuOptions.addAll(menu);
        menuOptions.add("2.Checkin book");
        menuOptions.add(quitMessage);
        ArrayList<String> menuTest = GetMenuOutputStream(sample);

        assertThat(menuTest, Is.is(menu));
    }

    //    @Test
    public void shouldCheckListBooks()
    {
        Biblioteca sample= new Biblioteca();
        outContent.reset();
        sample.DisplayBooks();

        ArrayList<String> books = new ArrayList<String>();
        ArrayList<String> bookTest = new ArrayList<String>();

        shouldCheckListBooksHelper(books, bookTest);

        assertThat(bookTest, Is.is(books));


    }
*/
}
