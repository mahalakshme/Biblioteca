package com.twu.biblioteca;

/**
 * Created by mahalaks on 15/01/15.
 */
public class BibliotecaInterface {

    String successfulCheckoutMessage = "Thank you! Enjoy";
    String unsuccessfulCheckoutMessage = "That item is not available.";
    String successfulReturnMessage = "Thank you for returning";
    String unsuccessfulReturnMessage = "That is not a valid item to return.";
    String findingUserUnsuccessfulMessage = "Book is not checkedout or entered AccessionNo not valid.";

    Library bangalorePublicLibrary;
    Menu menu;
    User loggedinUser;

    public BibliotecaInterface(Menu menu, Library publicLibrary)
    {
        this.menu = menu;
        bangalorePublicLibrary = publicLibrary;
    }

    boolean IsUserLoggedin() {

        if (loggedinUser != null) {
            return true;
        } else {
            return false;
        }
    }

    boolean AreUserCredentialsValid(Credential credential) {

        for (User user : bangalorePublicLibrary.users) {
            if (user.getCredential().equals(credential)) {
                loggedinUser = user;
                menu.options.add("6.Display my info");
                menu.options.add("8.Logout");
                menu.options.remove("9.Login");
                if(user instanceof Librarian)
                {
                 menu.options.add("7.Find the user who has checkedout the book");
                }

                return true;
            }
        }

        return false;
    }

    String CheckoutBook(String accessionNo) {

        Book book = bangalorePublicLibrary.CheckoutBook(accessionNo);
        if(book != null)
        {
            loggedinUser.setCheckedoutItems(book);
           return successfulCheckoutMessage;
        }
        else
        {
            return unsuccessfulCheckoutMessage;
        }
    }

    String ReturnBook(String accessionNo) {

        for (Book book : bangalorePublicLibrary.books) {
            if(book.getAccessionNo().equals(accessionNo))
            {
               if(bangalorePublicLibrary.Return(book) && loggedinUser.getCheckedoutItems() !=null && loggedinUser.getCheckedoutItems().contains(book))
               {
                   loggedinUser.getCheckedoutItems().remove(book);
                   return successfulReturnMessage;
               }
                else
               {
                   return unsuccessfulReturnMessage;
               }
            }
        }

        return unsuccessfulReturnMessage;
    }

    String CheckoutMovie(String accessionNo) {

         Movie movie = bangalorePublicLibrary.CheckoutMovie(accessionNo);
        if(movie != null)
        {
            loggedinUser.setCheckedoutItems(movie);
            return successfulCheckoutMessage;
        }
        else
        {
            return unsuccessfulCheckoutMessage;
        }
    }

    String FindUserWhoHasCheckedoutBook(String accessionNo) {
        String userInfo = bangalorePublicLibrary.FindUserWhoHasCheckedoutBook(accessionNo);
        if(userInfo != null)
        {
            return userInfo;
        }
        else
        {
            return findingUserUnsuccessfulMessage;
        }
    }

    boolean isLoggedinUserLibrarian() {
        return loggedinUser instanceof Librarian;
    }

    void Logout() {
        loggedinUser = null;
        menu.options.add("9.Login");
    }
}
