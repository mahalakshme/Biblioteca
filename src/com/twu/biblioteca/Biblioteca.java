package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by mahalaks on 10/01/15.
 */
public class Biblioteca {

    ArrayList<String> menu = new ArrayList<String>();


    public Biblioteca()
    {
        System.out.print("Welcome");
        CreateMenu();
    }

    private void CreateMenu() {
        menu.add("List Books");
        menu.add("Checkout Book");
    }

    public void DisplayMenu() {
        for (String option : menu) {
            System.out.println(option);
        }

    }
}
