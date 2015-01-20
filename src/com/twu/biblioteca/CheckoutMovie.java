package com.twu.biblioteca;

/**
 * Created by mahalaks on 18/01/15.
 */
public class CheckoutMovie extends Option {

    public CheckoutMovie(BibliotecaInterface bibliotecaInterface, UserManager userManager) {
        super(bibliotecaInterface, userManager);
        displayName = "6.Checkout Movie";
    }

    @Override
    void execute() {
        if(userManager.IsUserLoggedin()) {
            bibliotecaInterface.CheckoutMovie(userManager.GetLoggedinUser());
        }
        else
        {
          bibliotecaInterface.PrintMessage(Messages.NotLoggedinMessage);
        }
    }
}
