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

        Biblioteca sample = new Biblioteca(inContent, outContent);


        ArrayList<String> welcomeOptionsTest = new ArrayList<String>();
        GenerateListFromOutputStream(welcomeOptionsTest);
        ArrayList<String> welcomeOptions = new ArrayList<String>();

        welcomeOptions.add(0, welcomeMessage);
        welcomeOptions.addAll(menu);
        welcomeOptions.add(quitMessage);

        assertThat(welcomeOptionsTest, Is.is(welcomeOptions));
    }

     @Test
    public void shouldBeAbleToChooseOption() throws IOException {
         byte[] option = "1 N".getBytes();
         inContent = new ByteArrayInputStream(option);
         outContent.reset();

        new Biblioteca(inContent, outContent);

        ArrayList<String> books = new ArrayList<String>();
        ArrayList<String> bookTest = new ArrayList<String>();

         books.add(0, welcomeMessage);
         books.addAll(menu);
         books.add(quitMessage);


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

        new Biblioteca(inContent, outContent);

        String expected = welcomeMessage + "\n" + menu.get(0) + "\n" + quitMessage + "\n" + invalidOption + "\n" + continueMessage + "\n";

        assertThat(outContent.toString(), Is.is(expected));
    }

    @Test
    public void shouldBeAbleToContinueChoosingOptionsUntilQuit()
    {
        byte[] option = "1 Y 1 N".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent, outContent);

        ArrayList<String> books = new ArrayList<String>();
        ArrayList<String> bookTest = new ArrayList<String>();
        ArrayList<String> expected = new ArrayList<String>();

        expected.add(0, welcomeMessage);
        expected.addAll(menu);
        expected.add(quitMessage);

        shouldCheckListBooksHelper(books, bookTest);

        expected.addAll(books);
        expected.add(continueMessage);
        expected.addAll(menu);
        expected.add(quitMessage);
        expected.addAll(books);
        expected.add(continueMessage);

        assertThat(bookTest, Is.is(expected));
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
        String fileName = "books";

        String line;

        try {
            FileReader fileReader =
                    new FileReader(fileName);

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
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
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
