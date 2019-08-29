package ru.otus.boot.bookstore.exception;

public class ExistEntityException extends BookStoreException {
    public ExistEntityException(String s) {
        super(s);
    }
}
