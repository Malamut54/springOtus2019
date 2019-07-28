package ru.otus.boot.hw6.util;

import ru.otus.boot.hw6.exception.EmptyListException;

import java.util.List;

public final class Checker {
    private Checker () {}

    public static <T> List<T> checkEmptyList(List<T> list) {
        if (list.isEmpty()) {
            throw new EmptyListException("Nothing to found");
        }
        return list;
    }
}
