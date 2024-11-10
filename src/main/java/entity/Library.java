package entity;

import DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Library {
    public static List<Book> bookList = new ArrayList<>();

    public static void addBookFromInput() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Write the name of the book (type 'exit' to stop)! ");
                String title = scan.nextLine();
                if (title.equalsIgnoreCase("exit")) {
                    break;
                }

                boolean checkNameAndSurname = false;
                String author = "";
                while (!checkNameAndSurname) {
                    System.out.print("Write the author's name! ");
                    author = scan.nextLine();
                    if (author.matches(".*\\d.*")) {
                        System.out.println("The name cannot contain numbers!");
                    } else {
                        checkNameAndSurname = true;
                    }
                }
                System.out.print("Write the year of publication of the book! ");
                int publicationYear = scan.nextInt();
                scan.nextLine();
                Book book = new Book(title, author, publicationYear);
                bookList.add(book);
                System.out.println("Book added to the library: " + book);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the correct format!");
                scan.nextLine();

            }

        }
    }


}


