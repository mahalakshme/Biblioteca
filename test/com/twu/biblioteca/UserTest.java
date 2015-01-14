package com.twu.biblioteca;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by mahalaks on 13/01/15.
 */
public class UserTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent = new ByteArrayInputStream("My string".getBytes());

    String successfulCheckoutMessage = "Thank you! Enjoy";

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        inContent.reset();
        System.setIn(inContent);
    }

    @Test
    public void checkUser()
    {
        User user = new User("123-1234", "1009", "maha", "mahalakshme@gmail.com", "9600272894");
        assertThat(user.getId(), Is.is("123-1234"));
        assertThat(user.getPassword(), Is.is("1009"));
        assertThat(user.getName(), Is.is("maha"));
        assertThat(user.getEmailId(), Is.is("mahalakshme@gmail.com"));
        assertThat(user.getPhoneNo(), Is.is("9600272894"));
    }

    @Test
    public void shouldBeAbleToLogin()
    {
        byte[] option = "2 123-1234 1009 100 q".getBytes();
        inContent = new ByteArrayInputStream(option);
        outContent.reset();

        new Biblioteca(inContent);

        assertThat(outContent.toString(), containsString(successfulCheckoutMessage));
    }
}
