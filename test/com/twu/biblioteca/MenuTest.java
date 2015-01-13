package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by mahalaks on 13/01/15.
 */
public class MenuTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent = new ByteArrayInputStream("My string".getBytes());

    ArrayList<String> menu = new ArrayList<String>();

    @Before
    public void CreateMenu()
    {
        menu.add("1.List Books");
        menu.add("2.Checkout Book");
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        inContent.reset();
        System.setIn(inContent);
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

 }
