package com.twu.biblioteca;

/**
 * Created by mahalaks on 18/01/15.
 */
public class ReturnBook extends Option {

    public ReturnBook(BibliotecaInterface bibliotecaInterface, UserManager userManager) {
        super(bibliotecaInterface, userManager);
        displayName = "4.Return book";
    }

    @Override
    void execute() {
        if(userManager.IsUserLoggedin()) {
            bibliotecaInterface.ReturnBook(userManager.GetLoggedinUser());
        }
        else
        {
            bibliotecaInterface.PrintMessage(new NotLoggedinMessage());
        }
    }
}
