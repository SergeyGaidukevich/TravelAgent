package com.gaidukevich.tragent.repository.exception;

public class EntityNotFoundException extends RepositoryException {
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(Throwable e) {
        super(e);
    }

    public EntityNotFoundException(String s) {
        super(s);
    }
}
