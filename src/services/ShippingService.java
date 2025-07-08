package services;

import model.PaperBook;

public class ShippingService {

    public boolean shipPaperBook(PaperBook book, String address, int quantity) {
        System.out.println("book store: Preparing to ship physical book...");
        System.out.println("book store: Shipping " + quantity + " copies of '" 
                           + book.getTitle() + "' to address: " + address);

        // Simulate shipping process
        boolean deliverySuccessful = simulateDelivery();

        if (deliverySuccessful) {
            System.out.println("book store: Package successfully shipped to " + address);
        } else {
            System.out.println("book store: Failed to ship package to " + address);
        }

        return deliverySuccessful;
    }

    private boolean simulateDelivery() {
        // Simulate random delivery success for testing
        return true; // Always succeed for now
    }
}
