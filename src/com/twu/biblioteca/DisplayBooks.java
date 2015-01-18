package com.twu.biblioteca;

/**
 * Created by mahalaks on 18/01/15.
 */
public class DisplayBooks extends Option{

    public DisplayBooks(BibliotecaInterface bibliotecaInterface, UserManager userManager)
    {
        super(bibliotecaInterface, userManager);
        this.displayName = "2.Display books";
    }


    @Override
    void execute() {

        bibliotecaInterface.DisplayBooks();

    }
}
