package com.twu.biblioteca;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;


/**
 * Created by mahalaks on 10/01/15.
 */
public class BibliotecaTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    ByteArrayInputStream inContent = new ByteArrayInputStream("My string".getBytes());



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
        Biblioteca sample = new Biblioteca();

        ArrayList<String> welcomeOptionsTest = new ArrayList<String>();
        GenerateListFromOutputStream(welcomeOptionsTest);
        ArrayList<String> welcomeOptions = new ArrayList<String>();
        welcomeOptions.addAll(menu);
        welcomeOptions.add(0, "Welcome");
        assertThat(welcomeOptionsTest, Is.is(welcomeOptions));
    }

     @Test
    public void shouldBeAbleToChooseOption() throws IOException {
       Biblioteca sample = new Biblioteca();
       /*assertNotEquals(inContent.available(), 1);
        System.out.println(inContent.available());
       */

         byte[] option = "1".getBytes();
         inContent = new ByteArrayInputStream(option);
         outContent.reset();
         sample.ChooseOption(inContent);


        ArrayList<String> books = new ArrayList<String>();
        ArrayList<String> bookTest = new ArrayList<String>();

        shouldCheckListBooksHelper(books, bookTest);
        assertThat(bookTest, Is.is(books));

    }

    @Test
    public void shouldDisplayMenu() throws IOException {
        Biblioteca sample= new Biblioteca();


        ArrayList<String> menuTest = GetMenuOutputStream(sample);

        assertThat(menuTest, Is.is(menu));
    }

    private ArrayList<String> GetMenuOutputStream(Biblioteca sample) {

        outContent.reset();
        sample.DisplayMenu();
        ArrayList<String> menuTest = new ArrayList<String>();
        GenerateListFromOutputStream(menuTest);
        return menuTest;
    }

    private void GenerateListFromOutputStream(ArrayList<String> menuTest) {
        StringTokenizer option = new StringTokenizer(outContent.toString(), "\n");


        while (option.hasMoreElements()) {
            String opt = option.nextToken();
            menuTest.add(opt);
        }

    }

    @Test
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

    private void shouldCheckListBooksHelper(ArrayList<String> books, ArrayList<String> bookTest) {
        ReadFile(books);


        StringTokenizer bookList = new StringTokenizer(outContent.toString(), "\n");

        while (bookList.hasMoreElements()) {
            String book = bookList.nextToken();
            bookTest.add(book);
        }
    }

    @Test
    public void shouldBeAbleToAddOptionsToMenu() throws IOException {
        Biblioteca sample= new Biblioteca();
        sample.AddOptionsToMenu("2.Checkin book");
        menu.add("2.Checkin book");
        ArrayList<String> menuTest = GetMenuOutputStream(sample);

        assertThat(menuTest, Is.is(menu));
    }

    //@Test
    public void shouldCheckForInvalidOption()
    {
      //  Biblioteca sample = new Biblioteca();

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
}
