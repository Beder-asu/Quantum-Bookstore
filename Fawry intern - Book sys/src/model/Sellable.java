package model;

public interface Sellable {
    boolean deliver(String deliveryAddress, int quantity);
}