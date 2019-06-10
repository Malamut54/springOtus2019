package dao;

import java.util.List;
import java.util.Map;

public interface DaoCSV {
    Map<Integer, String> findAllQuestion();

    Map<Integer, String> findAllCorrectAnswer();

    Map<Integer, List<String>> findAllIAnswers();
}
