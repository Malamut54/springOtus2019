package dao;

import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.util.*;

public class QuestionDaoImpl implements QuestionDao {
    private String path;

    public QuestionDaoImpl(String path) {
        this.path = path;
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

        try (InputStream resourceAsStream = Class.class.getResourceAsStream(path)) {
            CSVReaderBuilder reader = new CSVReaderBuilder(new InputStreamReader(resourceAsStream));
            data = reader.build().readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

}
