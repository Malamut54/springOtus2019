package dao;

import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DaoCSVImpl implements DaoCSV {
    private String path;
    private FileReader fileReader;

    public void setPath(String path) {
        this.path = path;
    }


    @Override
    public Map<Integer, String> findAllQuestion() {
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

    private FileReader getFile(String pathToFile) {
        try {
            fileReader = new FileReader(pathToFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileReader;
    }

    private List<String[]> getDataFromCSV() {
        List<String[]> data = new ArrayList<>();
        CSVReaderBuilder reader = new CSVReaderBuilder(getFile(path));
        try {
            data = reader.build().readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
