package com.twu.biblioteca;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by mahalaks on 13/01/15.
 */
public class UserTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent = new ByteArrayInputStream("My string".getBytes());

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        inContent.reset();
        System.setIn(inContent);
    }

    @Test
    public void shouldBeAbleToCreateUser()
    {
        Credential credential = new Credential("123-1234", "1009");
        User user = new User(credential, "maha", "mahalakshme@gmail.com", "9600272894");
        assertThat(user.getCredential(), Is.is(credential));
        assertThat(user.getName(), Is.is("maha"));
        assertThat(user.getEmailId(), Is.is("mahalakshme@gmail.com"));
        assertThat(user.getPhoneNo(), Is.is("9600272894"));
    }
}
