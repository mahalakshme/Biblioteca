package com.twu.biblioteca;

/**
 * Created by mahalaks on 16/01/15.
 */
public class Librarian extends User{

    public Librarian(Credential credential, String name, String emailId, String phoneNo) {
        this.credential = credential;
        this.name = name;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
    }

}
