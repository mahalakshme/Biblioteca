package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by mahalaks on 12/01/15.
 */
public class Library {

    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private UserManager userManager;

    public Library(ArrayList<Book> books, ArrayList<Movie> movies, UserManager userManager) {

        this.userManager = userManager;
        this.books = books;
        this.movies = movies;
    }

    ArrayList<Book> getBooks() {
        return books;
    }

    ArrayList<Movie> getMovies() {
        return movies;
    }

    Book CheckoutBook(String accessionNo, User loggedinUser) {

        for (Book book : books) {
            if (book.getAccessionNo().equals(accessionNo) && (book.getIsAvailable())) {
                loggedinUser.setCheckedoutItems(book);
                book.setIsAvailable(false);
                return book;
            }

            if (book.getAccessionNo().equals(accessionNo) && !(book.getIsAvailable())) {
                return null;
            }
        }

        return null;
    }

    boolean Return(String accessionNo, User loggedinUser) {
        
        Book book = FindBook(accessionNo);

        if(book != null && loggedinUser.getCheckedoutItems() !=null && loggedinUser.getCheckedoutItems().contains(book))
         {
             loggedinUser.getCheckedoutItems().remove(book);
             book.setIsAvailable(true);
             return true;
        }
        else
        {
            return false;
        }
    }

    Movie CheckoutMovie(String accessionNo, User loggedinUser) {

        for (Movie movie : movies) {
            if (movie.getAccessionNo().equals(accessionNo) && (movie.getIsAvailable())) {
                loggedinUser.setCheckedoutItems(movie);
                movie.setIsAvailable(false);
                return movie;
            }

            if (movie.getAccessionNo().equals(accessionNo) && !(movie.getIsAvailable())) {
                return null;
            }
        }

        return null;
    }

    String FindUserWhoHasCheckedoutBook(String accessionNo) {

        for (User user : userManager.GetUsers()) {

            for (Item item : user.getCheckedoutItems()) {

                if (item.getAccessionNo().equals(accessionNo)) {
                    return "name:" + user.getName() + "\n" + "email id:" + user.getEmailId() + "\n" + "phone no:" + user.getPhoneNo();
                }
            }

        }

        return null;
    }

    Book FindBook(String accessionNo) {
        for (Book book : books) {
            if (book.getAccessionNo().equals(accessionNo)) {
               return book;

            }
        }

        return null;
    }
}
