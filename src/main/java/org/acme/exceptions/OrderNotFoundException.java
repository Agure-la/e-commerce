package org.acme.exceptions;

public class OrderNotFoundException extends IllegalArgumentException{
    public OrderNotFoundException(String msg){
        super(msg);
    }
}
