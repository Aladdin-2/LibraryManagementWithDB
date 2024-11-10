package entity;

public class Book {
    private static int idCounter = 1;
    private String title;
    private String author;
    private int publicationYear;
    private int bookID;

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        setPublicationYear(publicationYear);
        this.bookID = idCounter++;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(int publicationYear) {
        if (publicationYear < 2025 && publicationYear > 0) {
            this.publicationYear = publicationYear;
        } else {
            throw new IllegalArgumentException("Enter correctly. " + publicationYear + " cannot be");
        }
    }

    @Override
    public String toString() {
        return "Book{" + "ID=" + bookID + ". Title='" + title + '\'' + ", Author='" + author + '\'' + ", Year=" + publicationYear + '}';
    }
}
