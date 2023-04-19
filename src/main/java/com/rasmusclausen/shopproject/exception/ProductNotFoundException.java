package com.rasmusclausen.shopproject.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException (String message){
        super(message);
    }
}
