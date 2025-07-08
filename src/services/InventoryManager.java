package services;

import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.Sellable;

public class InventoryManager {
    private List<Book> inventory;

    public InventoryManager() {
        this.inventory = new ArrayList<>();
    }

    public void addBook(Book book) {
        inventory.add(book);
    }

    public void removeBook(Book book) {
        inventory.remove(book);
    }

    public void buyBook(String ISBN, int quantity, String deliveryContact) {
        if (quantity <= 0) {
            print("Invalid quantity: " + quantity);
            return;
        }
            if (deliveryContact == null || deliveryContact.isBlank()) {
            print("Invalid delivery contact.");
            return;
        }
        Book foundBook = findBookByISBN(ISBN);

        if (foundBook == null) {
            print("Book with ISBN " + ISBN + " not found in inventory.");
            return;
        }

        if (!(foundBook instanceof Sellable sellableBook)) {
            print("Book with ISBN " + ISBN + " is not sellable.");
            return;
        }

        double totalPrice = foundBook.getPrice() * quantity;

        
        boolean deliverySuccessful = sellableBook.deliver(deliveryContact, quantity);

        if (deliverySuccessful) {
            print("Purchase successful!");
            print("Book: " + foundBook.getTitle());
            print("Quantity: " + quantity);
            print("Total Price: $" + totalPrice);
            print("Delivery Contact: " + deliveryContact);
        } else {
            print("Purchase failed during delivery.");
        }
    }

    private Book findBookByISBN(String ISBN) {
        for (Book book : inventory) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }

    private void print(String message) {
        System.out.println("book store: " + message);
    }
}
