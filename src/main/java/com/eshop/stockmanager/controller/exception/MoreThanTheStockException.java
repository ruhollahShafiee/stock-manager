package com.eshop.stockmanager.controller.exception;

public class MoreThanTheStockException extends Exception {

    public MoreThanTheStockException() {
        super("The requested quantity of this product is more than the stock.");

    }

    public MoreThanTheStockException(String message) {
        super(message);
    }
}
