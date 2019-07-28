package ru.otus.boot.hw6.exception;

public class EmptyListException extends RuntimeException {
    public EmptyListException(String s) {
        super(s);
    }
}
