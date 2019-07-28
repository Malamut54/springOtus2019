package ru.otus.boot.hw6.exception;

public class ExistEntityException extends RuntimeException {
    public ExistEntityException(String s) {
        super(s);
    }
}
