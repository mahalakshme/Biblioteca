package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by mahalaks on 11/01/15.
 */
public class Menu {

    ArrayList<String> options;

    public Menu()
    {
        options = new ArrayList<String>();

        CreateMenu();

    }

    void CreateMenu() {
        options.add("1.List Books");
        options.add("2.Checkout Book");
    }

    void DisplayMenu() {
        for (String option : options) {
            System.out.println(option);
        }
    }

    void AddOptionsToMenu(String s) {
        options.add(s);
    }
}
