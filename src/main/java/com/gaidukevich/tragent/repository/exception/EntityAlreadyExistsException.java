package com.gaidukevich.tragent.repository.exception;

public class EntityAlreadyExistsException extends RepositoryException {
    public EntityAlreadyExistsException() {
    }

    public EntityAlreadyExistsException(Throwable e) {
        super(e);
    }

    public EntityAlreadyExistsException(String s) {
        super(s);
    }
}
