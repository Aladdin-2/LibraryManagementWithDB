package DBProcess;

import DBConnection.DBConnection;
import entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static entity.Library.addBookFromInput;
import static entity.Library.bookList;

public class DBProcess {
    private static final Connection connection = DBConnection.getConnection();
    private static PreparedStatement preparedStatement = null;

    public static void createLibraryTable() {
        String query = "CREATE TABLE IF NOT EXISTS Library(bookID INT PRIMARY KEY AUTO_INCREMENT," + "title VARCHAR(255), author VARCHAR(255),publicationYear INT)";
        try (PreparedStatement preparedStatement1 = connection.prepareStatement(query)) {
            preparedStatement.execute();
            System.out.println("Library table created successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Library table: " + e.getMessage());
        }
    }


    public static void insertBook() {
        addBookFromInput();
        String query = "INSERT INTO Library(title, author, publicationYear) VALUES(?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Book book : bookList) {
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setInt(3, book.getPublicationYear());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            System.out.println("Book inserted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting book: " + e.getMessage());
        }
    }

    public static void deleteBook() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the user's ID to delete ");
        int id = scan.nextInt();
        String query = "DELETE from Library WHERE bookID=? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Book deleted!");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting book: " + e.getMessage());
        }
    }


    public static void updateBook() {
        addBookFromInput();
        String query = "UPDATE Library SET title=?,author=?,publicationYear=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Book book : bookList) {
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setInt(3, book.getPublicationYear());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            System.out.println("Book update successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating book: " + e.getMessage());
        }
    }

    public static void showBooksFromDB() {
        String query = "SELECT*FROM Library";
        try (PreparedStatement preparedStatement1 = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                int bookID = resultSet.getInt("bookID");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int publicationYear = resultSet.getInt("publicationYear");

                System.out.println("Book ID: " + bookID);
                System.out.println("Title: " + title);
                System.out.println("Author: " + author);
                System.out.println("Publication Year: " + publicationYear);
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading books: " + e.getMessage());
        }
    }

    public static void shoBokMenu() {
        Scanner scan = new Scanner(System.in);
        int choice;


        LABEL_WHILE:
        while (true) {
            System.out.println("Choice!");
            System.out.println("1. Adding a book: ");
            System.out.println("2. Deleting a book: ");
            System.out.println("3. Updating a book: ");
            System.out.println("4. Showing a book: ");
            System.out.println("5. Exit! ");
            choice = scan.nextInt();
            switch (choice) {
                case 1: {
                    insertBook();

                    break;
                }
                case 2: {
                    deleteBook();
                    break;
                }
                case 3: {
                    updateBook();
                    break;
                }
                case 4: {
                    showBooksFromDB();
                    break;
                }
                case 5: {
                    System.out.println("Process terminated!");
                    break LABEL_WHILE;
                }
            }

        }
    }
}

