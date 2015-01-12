package com.twu.biblioteca;



import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by mahalaks on 10/01/15.
 */
public class Biblioteca {

    // ArrayList<String> menu = new ArrayList<String>();

    Menu menu;
    String bookStore;
    enum Status {successful, unsuccessful, error}
    Status status;
    String booksAvailableFile = "books available";
    String bookStoreFile = "books";

    public Biblioteca() {

        bookStore = "books";
        menu = new Menu();
        System.out.print("Welcome");
        System.out.println();

        ChooseOption(System.in);
    }

    public Biblioteca(InputStream inContent) {

        menu = new Menu();
        System.out.print("Welcome");
        System.out.println();

        ChooseOption(inContent);
    }



    private void DisplayBooks() {

        String line;

        try {
            FileReader fileReader =
                    new FileReader(bookStore);

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
              System.out.println(line);
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            bookStore + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + bookStore + "'");

        }

    }

    private void Checkout(String accessionNo) {


        removeBookWithAccessionNo(accessionNo);

        if(status == Status.successful) {
            System.out.println("Thank you! Enjoy the book");
        }
        else if(status != Status.error) {
            System.out.println("That book is not available.");
        }
    }

    private  void removeBookWithAccessionNo(String lineToRemove) {

        try {

            boolean bookPresent = false;
            File inFile = new File(booksAvailableFile);

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                status = Status.error;
                return;
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + "a");

            BufferedReader br = new BufferedReader(new FileReader(booksAvailableFile));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;


            while ((line = br.readLine()) != null) {

                StringTokenizer book = new StringTokenizer(line, ":");
                if (!book.nextToken().equals(lineToRemove)) {

                    pw.println(line);
                    pw.flush();
                }
                else
                {
                    status = Status.successful;
                }

            }
                pw.close();
                br.close();

                //Delete the original file
                if (!inFile.delete()) {
                    System.out.println("Could not delete file");
                    return;
                }

                //Rename the new file to the filename the original file had.
                if (!tempFile.renameTo(inFile))
                    System.out.println("Could not rename file");

            }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        }

    private void ChooseOption(InputStream inContent) {

        String option;
        String shouldContinue = "Y";
        Scanner input = new Scanner(inContent);

            while(shouldContinue.charAt(0) == 'Y')
            {
                menu.DisplayMenu();
                System.out.println("Enter q to Quit");
                option = input.next();
             switch (option.charAt(0))
             {
                 case '1':
                     DisplayBooks();
                     break;
                 case '2':
                     System.out.println("Enter the accession no of the book to be checked out:");
                     String accessionNo = input.next();
                     Checkout(accessionNo);
                     break;
                 case '3':
                     System.out.println("Enter the accession no of the book to be returned:");
                     String accessionNoReturn = input.next();
                     Return(accessionNoReturn);
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

    private void Return(String accessionNoReturn) {

        String bookDetails = new String();
        if(DoesBookStoreContainFile(accessionNoReturn, bookDetails))
        {
          AddToBooksAvailable(accessionNoReturn);
            System.out.println("Thank you for returning the book.");
        }
        else
        {
            System.out.println("That is not a valid book to return.");
        }
    }

    private void AddToBooksAvailable(String accessionNoReturn) {

        try{

            PrintWriter out = new PrintWriter(new FileWriter(booksAvailableFile));
            out.println("the text");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean DoesBookStoreContainFile(String accessionNoReturn, String bookDetails)
    {
        String line;
        boolean isContains = false;
        try {
            FileReader fileReader =
                    new FileReader(booksAvailableFile);

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                StringTokenizer book = new StringTokenizer(line, ":");
                if(book.nextToken().equals(accessionNoReturn))
                {
                    bookDetails = line;
                    isContains = true;
                    break;
                }
            }

            bufferedReader.close();
            return isContains;
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            booksAvailableFile + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + booksAvailableFile + "'");
        }
    }


}
