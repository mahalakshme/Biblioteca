package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by mahalaks on 11/01/15.
 */
public class Menu {

    ArrayList<Option> options;

    public Menu(BibliotecaInterface bibliotecaInterface, UserManager userManager)
    {
        options = new ArrayList<Option>();
        options.add(new Login(bibliotecaInterface, userManager));
        options.add(new DisplayBooks(bibliotecaInterface, userManager));
        options.add(new CheckoutBook(bibliotecaInterface, userManager));
        options.add(new ReturnBook(bibliotecaInterface, userManager));
        options.add(new DisplayMovies(bibliotecaInterface, userManager));
        options.add(new CheckoutMovie(bibliotecaInterface, userManager));
        options.add(new DisplayLoggedinUserInfo(bibliotecaInterface, userManager));
        options.add(new FindUserWhoHasCheckedoutBook(bibliotecaInterface, userManager));
        options.add(new Logout(bibliotecaInterface, userManager));
    }

    ArrayList<Option> GetOptions()
    {
        return options;
    }

    public boolean ChooseOption(int option) {

        if((option > 0) && (option <= options.size())) {
            options.get(option - 1).execute();
            return true;
        }
        else
        {
            return false;
        }
    }
}
