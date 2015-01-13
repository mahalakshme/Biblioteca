package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by mahalaks on 13/01/15.
 */
public class User {
    private final String id;
    private String password;
    private String name;
    private String emailId;
    private String phoneNo;
    private ArrayList<Item> checkedoutItems;

    public User(String id, String password, String name, String emailId, String phoneNo, ArrayList<Item> checkedoutItems) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.checkedoutItems = checkedoutItems;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public ArrayList<Item> getCheckedoutItems() {
        return checkedoutItems;
    }

    public void setCheckedoutItems(Item item) {
        if(checkedoutItems == null) {
            this.checkedoutItems = new ArrayList<Item>();
        }

        this.checkedoutItems.add(item);
    }



//    public boolean isLoggedinStatus() {
//        return loggedinStatus;
//    }
//
//    public void setLoggedinStatus(boolean loggedinStatus) {
//        this.loggedinStatus = loggedinStatus;
//    }
}
