package com.twu.biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mahalaks on 10/01/15.
 */
public class Biblioteca {

    ArrayList<String> menu = new ArrayList<String>();


    public Biblioteca() {
        System.out.print("Welcome");
        System.out.println();
        CreateMenu();
        ChooseOption(System.in);
    }

    public Biblioteca(InputStream inContent) {
        System.out.print("Welcome");
        System.out.println();
        CreateMenu();
        ChooseOption(inContent);
    }

    private void CreateMenu() {
        menu.add("1.List Books");
        menu.add("2.Checkout Book");
    }

    private void DisplayMenu() {
        for (String option : menu) {
            System.out.println(option);
        }

    }

    private void DisplayBooks() {
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

    public void AddOptionsToMenu(String s) {
        menu.add(s);
    }

    public void ChooseOption(InputStream inContent) {

        String option;
        String shouldContinue = "Y";
        Scanner input = new Scanner(inContent);

            while(shouldContinue.charAt(0) == 'Y')
            {
                DisplayMenu();
                System.out.println("Enter q to Quit");
                option = input.next();
             switch (option.charAt(0))
             {
                 case '1':
                     DisplayBooks();
                     break;
                 case 'q':
                     return;
                 default:
                     System.out.println("Select a valid option!");

             }
                System.out.println("Do you want to continue?<Y/N>");
                shouldContinue = input.next();

        }

    }
}
