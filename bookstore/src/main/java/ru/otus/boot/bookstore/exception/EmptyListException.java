package ru.otus.boot.bookstore.exception;

public class EmptyListException extends BookStoreException {
    public EmptyListException(String s) {
        super(s);
    }
}
