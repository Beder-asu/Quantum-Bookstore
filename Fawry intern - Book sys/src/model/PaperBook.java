package model;

import services.ShippingService;

public class PaperBook extends Book implements Sellable {
    private int stock;

    public PaperBook(String ISBN, String title, String author, int year, double price, int stock) {
        super(ISBN, title, author, year, price);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void reduceStock(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
        } else {
            throw new IllegalArgumentException("Insufficient stock");
        }
    }

    public void increaseStock(int quantity) {
        stock += quantity;
    }

    @Override
    public boolean deliver(String deliveryAddress, int quantity) {
    if (stock < quantity) {
        System.out.println("book store: Cannot deliver. Requested: " + quantity + ", Available: " + stock);
        return false;
    }

    // Reduce stock
    stock -= quantity;

    // Attempt delivery
    ShippingService shippingService = new ShippingService();
    boolean success = shippingService.shipPaperBook(this, deliveryAddress, quantity);

    if (!success) {
        // Rollback stock if delivery failed
        stock += quantity;
        System.out.println("Book store: Delivery failed. Stock restored.");
    }

    return success;
    }

}
