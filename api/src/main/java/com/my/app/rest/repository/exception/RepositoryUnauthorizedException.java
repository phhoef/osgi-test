package com.my.app.rest.repository.exception;

public class RepositoryUnauthorizedException extends RuntimeException
{
    public RepositoryUnauthorizedException() {
    }

    public RepositoryUnauthorizedException(String message) {
        super(message);
    }

    public RepositoryUnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryUnauthorizedException(Throwable cause) {
        super(cause);
    }
}
