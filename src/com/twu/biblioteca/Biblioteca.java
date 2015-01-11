package com.twu.biblioteca;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by mahalaks on 10/01/15.
 */
public class Biblioteca {

    ArrayList<String> menu = new ArrayList<String>();


    public Biblioteca() {
        System.out.print("Welcome");

       // for(int clear = 0; clear < 1000; clear++)
        {
            System.out.print("\r") ;
        }
       /* try {
            Runtime.getRuntime().exec("clear");
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        // System.out.println();
        CreateMenu();
        DisplayMenu();
        ChooseOption(System.in);
    }

    public Biblioteca(InputStream inContent, ByteArrayOutputStream outContent) {
        System.out.print("Welcome");
        System.out.println();
        CreateMenu();
        DisplayMenu();
        outContent.reset();
        ChooseOption(inContent);
    }

    private void CreateMenu() {
        menu.add("1.List");
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

    public void AddOptionsToMenu(String s) {
        menu.add(s);
    }

    public void ChooseOption(InputStream inContent) {

        int data;
        try {
            data = inContent.read();

            switch ((char)data)
            {
                case '1':DisplayBooks();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
