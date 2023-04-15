package com.rasmusclausen.storeproject.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException (String message){
        super(message);
    }
}
