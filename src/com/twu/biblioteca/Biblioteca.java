package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public void DisplayBooks() {
        // The name of the file to open.
        String fileName = "books";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
              System.out.println(line);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

    }
}
