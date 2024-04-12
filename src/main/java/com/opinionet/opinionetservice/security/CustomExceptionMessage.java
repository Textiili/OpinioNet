package com.opinionet.opinionetservice.security;

public class CustomExceptionMessage extends RuntimeException{
    public CustomExceptionMessage(String message) {
        super(message);
    }
}

