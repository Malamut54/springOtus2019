package ru.otus.hw2.dao;

import java.util.List;
import java.util.Map;

public interface QuestionDao {
    Map<Integer, String> findAllQuestion();

    Map<Integer, String> findAllCorrectAnswer();

    Map<Integer, List<String>> findAllIAnswers();
}
