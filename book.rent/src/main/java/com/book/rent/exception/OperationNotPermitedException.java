package com.book.rent.exception;

public class OperationNotPermitedException extends RuntimeException {
    public OperationNotPermitedException(String msg) {
        super(msg);
    }
}
