package com.example.storeeverything.exceptions;

public class SharedByLinkNotFoundException extends RuntimeException {
    public SharedByLinkNotFoundException() {
        super("Couldn't find shared information. Link may be incorrect or this information isn't shared anymore.");
    }

    public SharedByLinkNotFoundException(String message) {
        super(message);
    }
}
