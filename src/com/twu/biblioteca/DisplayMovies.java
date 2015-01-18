package com.twu.biblioteca;

/**
 * Created by mahalaks on 18/01/15.
 */
public class DisplayMovies extends Option{

    public DisplayMovies(BibliotecaInterface bibliotecaInterface, UserManager userManager) {
        super(bibliotecaInterface, userManager);
        this.displayName = "5.Display movies";
    }

    @Override
    void execute() {
        bibliotecaInterface.DisplayMovies();
    }
}
