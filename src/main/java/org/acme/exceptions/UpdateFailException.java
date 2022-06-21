package org.acme.exceptions;

public class UpdateFailException extends IllegalArgumentException{
    public UpdateFailException(String msg){
        super(msg);
    }
}
