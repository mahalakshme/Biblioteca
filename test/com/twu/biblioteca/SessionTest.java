package com.twu.biblioteca;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mahalaks on 13/01/15.
 */
public class SessionTest {

    @Test
    public void shouldBeAbleToCreateSession()
    {
       Session session = new Session("123-1234");
        assertThat(session.getUserId(), Is.is("123-1234"));
    }


}
