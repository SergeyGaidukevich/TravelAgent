package com.gaidukevich.tragent.repository.exception;

public class RepositoryException extends RuntimeException {
    RepositoryException() {
    }

    RepositoryException(Throwable e) {
        super(e);
    }

    RepositoryException(String s) {
        super(s);
    }
}
