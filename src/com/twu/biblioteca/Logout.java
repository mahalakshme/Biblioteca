package com.twu.biblioteca;

/**
 * Created by mahalaks on 18/01/15.
 */
public class Logout extends Option {

    public Logout(BibliotecaInterface bibliotecaInterface, UserManager userManager) {
        super(bibliotecaInterface, userManager);
        displayName = "9.Logout";
    }

    @Override
    void execute() {
        if(userManager.GetLoggedinUser() != null) {
            userManager.SetLoggedinUser(null);
            bibliotecaInterface.PrintMessage(new LogoutMessage());
        }
        else
        {
            bibliotecaInterface.PrintMessage(new AlreadyLoggedoutMessage());
        }
        }
}
