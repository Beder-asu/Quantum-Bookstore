package model;

public abstract class Book{
    private String ISBN;

    private String title;
    private String author;
    private int year;
    private double price;

    public Book(String ISBN, String title, String author, int year, double price) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    // Setter methods
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString method
    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}

