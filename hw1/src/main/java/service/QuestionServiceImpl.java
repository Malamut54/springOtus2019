package service;

import dao.QuestionDao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class QuestionServiceImpl implements QuestionService {
    private QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public Map<Integer, String> getAllQuestion() {
        return questionDao.findAllQuestion();
    }

    @Override
    public Map<Integer, List<String>> getAllAnswer() {
        Map<Integer, List<String>> allIAnswers = questionDao.findAllIAnswers();
        for (List<String> value : allIAnswers.values()) {
            Collections.shuffle(value);
        }
        return allIAnswers;
    }

    @Override
    public Map<Integer, String> getAllCorrectAnswer() {
        return questionDao.findAllCorrectAnswer();
    }

    @Override
    public Boolean isCorrect(String answer, String correctAnswer) {
        return answer.equals(correctAnswer);
    }

}
