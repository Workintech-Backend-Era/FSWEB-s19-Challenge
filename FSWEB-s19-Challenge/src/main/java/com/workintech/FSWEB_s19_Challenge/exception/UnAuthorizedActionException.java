package com.workintech.FSWEB_s19_Challenge.exception;

public class UnAuthorizedActionException extends RuntimeException{

    public UnAuthorizedActionException(String message){
        super(message);
    }
}
