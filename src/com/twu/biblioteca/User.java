package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by mahalaks on 13/01/15.
 */
public class User {
    protected String name;
    protected String emailId;
    protected String phoneNo;
    protected ArrayList<Item> checkedoutItems;
    protected Credential credential;

    public User(Credential credential, String name, String emailId, String phoneNo) {
        this.credential = credential;
        this.name = name;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
    }

    String getName() {
        return name;
    }

    String getEmailId() {
        return emailId;
    }

    String getPhoneNo() {
        return phoneNo;
    }

    ArrayList<Item> getCheckedoutItems() {
        if(checkedoutItems != null)
        {
            return checkedoutItems;
        }
        else
        {
            return null;
        }
    }

    void setCheckedoutItems(Item item) {
        if(checkedoutItems == null) {
            this.checkedoutItems = new ArrayList<Item>();
        }

        this.checkedoutItems.add(item);
    }

    Credential getCredential() {
        return credential;
    }
}
