package services;

public class MailService {

    public boolean sendEBook(String email, String bookTitle, String fileType) {
        System.out.println("book store: Preparing to send E-Book...");
        System.out.println("book store: Sending '" + bookTitle + "' (" + fileType + ") to email: " + email);

        // Simulate sending process
        boolean deliverySuccessful = simulateDelivery();

        if (deliverySuccessful) {
            System.out.println("book store: E-Book successfully delivered to " + email);
        } else {
            System.out.println("book store: Failed to deliver E-Book to " + email);
        }

        return deliverySuccessful;
    }

    private boolean simulateDelivery() {
        // Simulate random delivery success for testing
        return true; // Always succeed for now
    }
}
