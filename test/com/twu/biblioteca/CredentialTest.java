package com.twu.biblioteca;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Created by mahalaks on 16/01/15.
 */
public class CredentialTest {

    @Test
    public void shouldCheckEqual()
    {
        Credential credential1 = new Credential("123-1234", "1009");
        Credential credential2 = new Credential("123-1234", "1009");
        assertThat(credential1, Is.is(credential2));
    }
}
