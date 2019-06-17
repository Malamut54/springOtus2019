package ru.otus.hw2.dao;

import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class QuestionDaoImpl implements QuestionDao {
    private Locale locale;
    @Value("${questionEN}")
    String pathEn;
    @Value("${questionRU}")
    String pathRu;

    private static final String RUSSIAN_LANGUAGE = "Russian";

    public QuestionDaoImpl(Locale locale) {
        this.locale = locale;
    }

    @Override
    public Map<Integer, String> findAllQuestion()  {
        int indexNumber = 0;
        Map<Integer, String> result = new HashMap<>();
        List<String[]> temp = getDataFromCSV();

        for (String[] strings : temp) {
            for (String string : strings) {
                result.put(indexNumber++, string.substring(0, string.indexOf(";")));
            }
        }
        return result;

    }

    @Override
    public Map<Integer, String> findAllCorrectAnswer() {
        int indexCorrectAnswer = 1;
        int indexNumber = 0;
        Map<Integer, String> result = new HashMap<>();
        List<String[]> temp = getDataFromCSV();
        String delimiter = ";";

        for (String[] strings : temp) {
            for (String string : strings) {
                result.put(indexNumber++, Arrays.asList(string.split(delimiter)).get(indexCorrectAnswer));
            }
        }
        return result;

    }

    @Override
    public Map<Integer, List<String>> findAllIAnswers() {
        Map<Integer, List<String>> result = new HashMap<>();
        List<String[]> temp = getDataFromCSV();
        String delimiter = ";";
        int indexNumber = 0;

        for (String[] strings : temp) {
            for (String string : strings) {
                result.put(indexNumber++, Arrays.asList(string.substring(string.indexOf(";") + 1).split(delimiter)));
            }
        }
        return result;
    }

    private List<String[]> getDataFromCSV() {
        List<String[]> data = new ArrayList<>();
        try (InputStream resourceAsStream = getQuestionLocalized(locale)) {
            CSVReaderBuilder reader = new CSVReaderBuilder(new InputStreamReader(resourceAsStream));
            data = reader.build().readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    private InputStream getQuestionLocalized(Locale locale) {
        return locale.getDisplayLanguage().equals(RUSSIAN_LANGUAGE) ? Class.class.getResourceAsStream(pathRu) : Class.class.getResourceAsStream(pathEn);
    }
}
