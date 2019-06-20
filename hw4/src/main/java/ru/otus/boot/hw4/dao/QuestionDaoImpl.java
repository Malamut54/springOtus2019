package ru.otus.boot.hw4.dao;

import com.opencsv.CSVReaderBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Service
@ConfigurationProperties(prefix = "path")
public class QuestionDaoImpl implements QuestionDao {
    private final Locale locale;
    @Getter
    @Setter
    private String questionEn;
    @Getter
    @Setter
    private String questionRu;

    private static final List<String> RUSSIAN_LANGUAGE = Arrays.asList("Russian", "русский");

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
        if (RUSSIAN_LANGUAGE.contains(locale.getDisplayLanguage())) {
            return Class.class.getResourceAsStream(questionRu);
        } else {
            return Class.class.getResourceAsStream(questionEn);
        }
    }
}
