package com.twu.biblioteca;

/**
 * Created by mahalaks on 18/01/15.
 */
public abstract class Option {

    BibliotecaInterface bibliotecaInterface;
    UserManager userManager;
    String displayName;

    public Option(BibliotecaInterface bibliotecaInterface, UserManager userManager)
    {
       this.bibliotecaInterface = bibliotecaInterface;
        this.userManager = userManager;
    }

    protected void create(BibliotecaInterface bibliotecaInterface)
    {
       this.bibliotecaInterface = bibliotecaInterface;
    }

    abstract void execute();

    String getDisplayName()
    {
        return displayName;
    }
}
