package com.my.app.rest.repository.exception;

public class RepositoryItemNotFoundException extends Exception
{
    public RepositoryItemNotFoundException() {
    }

    public RepositoryItemNotFoundException(String message) {
        super(message);
    }

    public RepositoryItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryItemNotFoundException(Throwable cause) {
        super(cause);
    }
}
