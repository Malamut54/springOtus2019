package ru.otus.boot.hw5.exception;

public class EmptyListException extends RuntimeException {
    public EmptyListException(String s) {
        super(s);
    }
}
