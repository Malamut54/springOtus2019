package service;

import dao.DaoCSV;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class QuestionServiceImpl implements QuestionService {
    private DaoCSV daoCSV;

    public void setDaoCSV(DaoCSV daoCSV) {
        this.daoCSV = daoCSV;
    }

    @Override
    public Map<Integer, String> getAllQuestion() {
        return daoCSV.findAllQuestion();
    }

    @Override
    public Map<Integer, List<String>> getAllAnswer() {
        Map<Integer, List<String>> allIAnswers = daoCSV.findAllIAnswers();
        for (List<String> value : allIAnswers.values()) {
            Collections.shuffle(value);
        }
        return allIAnswers;
    }

    @Override
    public Map<Integer, String> getAllCorrectAnswer() {
        return daoCSV.findAllCorrectAnswer();
    }

    @Override
    public Boolean isCorrect(String answer, String correctAnswer) {
        return answer.equals(correctAnswer);
    }

}
