package com.twu.biblioteca;

/**
 * Created by mahalaks on 18/01/15.
 */
public class FindUserWhoHasCheckedoutBook extends Option {

    public FindUserWhoHasCheckedoutBook(BibliotecaInterface bibliotecaInterface, UserManager userManager) {
        super(bibliotecaInterface, userManager);
        displayName = "8.Find user who has checkedout book";
    }

    @Override
    void execute() {
        if(userManager.IsUserLoggedin())
        {

            bibliotecaInterface.FindUserWhoHasCheckedoutBook();
        }
        else
        {
            bibliotecaInterface.PrintMessage(Messages.NotLoggedinMessage);
        }
    }
}
