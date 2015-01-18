package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by mahalaks on 17/01/15.
 */
public class UserManager {

    private ArrayList<User> users = new ArrayList<User>();
    private User loggedinUser;

    public UserManager(ArrayList<User> users)
    {
        this.users = users;
    }

    User GetLoggedinUser()
    {
        return loggedinUser;
    }

    void SetLoggedinUser(User user)
    {
        this.loggedinUser = user;
    }

    ArrayList<User> GetUsers()
    {
        return users;
    }

    boolean IsUserLoggedin() {

        if (loggedinUser != null) {
            return true;
        } else {
            return false;
        }
    }

    boolean AreUserCredentialsValid(Credential credential) {

        for (User user : users) {
            if (user.getCredential().equals(credential)) {
                loggedinUser = user;
                return true;
            }
        }

        return false;
    }
}
