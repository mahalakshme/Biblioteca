package com.twu.biblioteca;

/**
 * Created by mahalaks on 14/01/15.
 */
public abstract class Item {

    protected String accessionNo;
    protected String name;
    protected Boolean isAvailable;

    Item(){}

    String getAccessionNo() {
        return accessionNo;
    }

    String getName() {
        return name;
    }

    boolean getIsAvailable() {
        return isAvailable;
    }

    void setIsAvailable(boolean available) {
        this.isAvailable = available;
    }



}
