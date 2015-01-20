package com.twu.biblioteca;

/**
 * Created by mahalaks on 18/01/15.
 */
public class Login extends Option {

    public Login(BibliotecaInterface bibliotecaInterface, UserManager userManager) {
        super(bibliotecaInterface, userManager);
        displayName = "1.Login";
    }

    @Override
    void execute() {
        if(userManager.GetLoggedinUser() == null) {
            Credential credential = bibliotecaInterface.RequestUserCredentials();
            if (userManager.AreUserCredentialsValid(credential)) {
                bibliotecaInterface.PrintMessage(Messages.LoginMessage);
            } else {
                  bibliotecaInterface.PrintMessage(Messages.InvalidCredentialsMessage);
            }
        }
        else
        {
            bibliotecaInterface.PrintMessage(Messages.AlreadyLoggedinMessage);
        }
    }
}
