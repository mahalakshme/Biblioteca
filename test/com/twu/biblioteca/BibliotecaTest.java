package com.twu.biblioteca;

import com.apple.crypto.provider.Debug;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThat;


/**
 * Created by mahalaks on 10/01/15.
 */
public class BibliotecaTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    ArrayList<String> menu = new ArrayList<String>();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Before
    public void CreateMenu()
    {

        menu.add("List Books");
        menu.add("Checkout Book");
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void shouldWelcome()
    {
        Biblioteca sample = new Biblioteca();
        assertEquals("Welcome", outContent.toString());
    }

    @Test
    public void shouldDisplayMenu() throws IOException {

        Biblioteca sample = new Biblioteca();
        outContent.reset();
        sample.DisplayMenu();
        ArrayList<String> menuTest = new ArrayList<String>();
        StringTokenizer option = new StringTokenizer(outContent.toString(), "\n");


        while (option.hasMoreElements()) {
            String opt = option.nextToken();
            menuTest.add(opt);
        }

        assertThat(menuTest, Is.is(menu));
    }

    @Test
    public void shouldCheckListBooks()
    {
      Biblioteca sample = new Biblioteca();
        outContent.reset();
        ArrayList<String> books = new ArrayList<String>();
        ArrayList<String> bookTest = new ArrayList<String>();
        ReadFile(books);
        sample.DisplayBooks();

        StringTokenizer bookList = new StringTokenizer(outContent.toString(), "\n");


        while (bookList.hasMoreElements()) {
            String book = bookList.nextToken();
            bookTest.add(book);
        }

        assertThat(bookTest, Is.is(books));


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
