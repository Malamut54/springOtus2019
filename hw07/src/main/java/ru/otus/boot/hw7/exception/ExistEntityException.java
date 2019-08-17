package ru.otus.boot.hw7.exception;

public class ExistEntityException extends RuntimeException {
    public ExistEntityException(String s) {
        super(s);
    }
}
