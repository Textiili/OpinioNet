package com.opinionet.opinionetservice.web;


public class CustomExceptionMessage extends RuntimeException{
    public CustomExceptionMessage(String message) {
        super(message);
    }
}

