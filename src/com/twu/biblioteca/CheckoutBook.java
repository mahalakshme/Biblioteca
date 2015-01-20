package com.twu.biblioteca;

/**
 * Created by mahalaks on 18/01/15.
 */
public class CheckoutBook extends Option {

    public CheckoutBook(BibliotecaInterface bibliotecaInterface, UserManager userManager)
    {
        super(bibliotecaInterface, userManager);
        displayName = "3.Checkout Book";
    }

    @Override
    void execute() {
        if(userManager.IsUserLoggedin()) {
            bibliotecaInterface.CheckoutBook(userManager.GetLoggedinUser());
        }
        else
        {
            bibliotecaInterface.PrintMessage(Messages.NotLoggedinMessage);
        }
    }
}
