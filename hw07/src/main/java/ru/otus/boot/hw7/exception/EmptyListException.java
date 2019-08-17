package ru.otus.boot.hw7.exception;

public class EmptyListException extends RuntimeException {
    public EmptyListException(String s) {
        super(s);
    }
}
