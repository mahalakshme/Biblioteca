package com.twu.biblioteca;

import java.io.*;
import java.util.Scanner;

/**
 * Created by mahalaks on 10/01/15.
 */
public class Biblioteca {

    Library bangalorePublicLibrary;
    Menu menu;

    public Biblioteca() {

        bangalorePublicLibrary = new Library();
        menu = new Menu();

        System.out.print("Welcome");
        System.out.println();

        ChooseOption(System.in);
    }

    public Biblioteca(InputStream inContent) {

        bangalorePublicLibrary = new Library();
        menu = new Menu();

        System.out.print("Welcome");
        System.out.println();

        ChooseOption(inContent);
    }


    private void ChooseOption(InputStream inContent) {

        String option;
        String shouldContinue = "Y";
        Scanner input = new Scanner(inContent);
        String accessionNo;

            while(shouldContinue.charAt(0) == 'Y')
            {
                menu.DisplayMenu();
                System.out.println("Enter q to Quit");
                option = input.next();
             switch (option.charAt(0))
             {
                 case '1':
                     bangalorePublicLibrary.DisplayBooks();
                     break;
                 case '2':
                     System.out.println("Enter the accession no of the book to be checked out:");
                     accessionNo = input.next();
                     bangalorePublicLibrary.Checkout(accessionNo);
                     break;
                 case '3':
                     System.out.println("Enter the accession no of the book to be returned:");
                     accessionNo = input.next();
                     bangalorePublicLibrary.Return(accessionNo);
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
