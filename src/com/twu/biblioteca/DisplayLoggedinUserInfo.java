package com.twu.biblioteca;

/**
 * Created by mahalaks on 18/01/15.
 */
public class DisplayLoggedinUserInfo extends Option{

    public DisplayLoggedinUserInfo(BibliotecaInterface bibliotecaInterface, UserManager userManager) {
        super(bibliotecaInterface, userManager);
        displayName = "7.Display my information";

    }

    @Override
    void execute() {
        if(userManager.IsUserLoggedin())
        {
            bibliotecaInterface.DiplayLoggedinUserInfo(userManager.GetLoggedinUser());
        }
        else
        {
            bibliotecaInterface.PrintMessage(new NotLoggedinMessage());
        }
    }
}
