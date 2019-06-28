package ru.otus.boot.hw5.exception;

public class ExistEntityException extends RuntimeException {
    public ExistEntityException(String s) {
        super(s);
    }
}
