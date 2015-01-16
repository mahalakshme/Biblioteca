package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by mahalaks on 12/01/15.
 */
public class Library {

    ArrayList<Book> books = new ArrayList<Book>();
    ArrayList<Movie> movies = new ArrayList<Movie>();
    ArrayList<User> users = new ArrayList<User>();

    public Library(ArrayList<Book> books, ArrayList<Movie> movies, ArrayList<User> users) {

        this.books = books;
        this.movies = movies;
        this.users = users;
    }

    Book CheckoutBook(String accessionNo) {

        for (Book book : books) {
            if(book.getAccessionNo().equals(accessionNo) && (book.getIsAvailable()))
            {
                book.setIsAvailable(false);
                return book;
            }

            if(book.getAccessionNo().equals(accessionNo) && !(book.getIsAvailable()))
            {
                return null;
            }
        }

        return null;
    }

    boolean Return(Book book) {

            if(!book.getIsAvailable()) {
                book.setIsAvailable(true);
                return true;
            }
            else
            {
                return false;
            }
    }

    Movie CheckoutMovie(String accessionNo) {

        for (Movie movie : movies) {
            if(movie.getAccessionNo().equals(accessionNo) && (movie.getIsAvailable()))
            {
                movie.setIsAvailable(false);
                return movie;
            }

            if(movie.getAccessionNo().equals(accessionNo) && !(movie.getIsAvailable()))
            {
                return null;
            }
        }

        return null;
    }

    String FindUserWhoHasCheckedoutBook(String accessionNo) {

        for (User user : users) {

            for (Item item : user.getCheckedoutItems()) {

                if(item.getAccessionNo().equals(accessionNo))
                {
                    return "name:" + user.getName() + "\n" + "email id:" + user.getEmailId() + "\n" + "phone no:" + user.getPhoneNo();
                }
            }

        }

        return null;

    }
}
