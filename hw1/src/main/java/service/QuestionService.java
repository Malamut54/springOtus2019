package service;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    Map<Integer, String> getAllQuestion();

    Map<Integer, List<String>> getAllAnswer();

    Map<Integer, String> getAllCorrectAnswer();

    Boolean isCorrect(String answer, String correctAnswer);
}