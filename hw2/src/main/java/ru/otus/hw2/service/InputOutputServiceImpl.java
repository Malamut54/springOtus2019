package ru.otus.hw2.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

@Service
public class InputOutputServiceImpl implements InputOutputService {
    private static final int MAX_QUESTION = 5;
    private QuestionService questionService;
    private UserService userService;
    private MessageSource messageSource;
    private Locale locale;
    private Scanner scanner = new Scanner(System.in);

    public InputOutputServiceImpl(QuestionService questionService, UserService userService, MessageSource messageSource) {
        this.questionService = questionService;
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @Override
    public void startExam() {
        Map<Integer, String> allQuestion = questionService.getAllQuestion();
        Map<Integer, List<String>> allAnswer = questionService.getAllAnswer();
        Map<Integer, String> allCorrectAnswer = questionService.getAllCorrectAnswer();

        int indexAnswer = 0;
        int answer = 0;
        int countCorrectAnswer = 0;

        greeting();

        for (int i = 0; i < MAX_QUESTION; i++) {
            System.out.println(allQuestion.get(i));

            for (String s : allAnswer.get(i)) {
                System.out.println(indexAnswer++ + " " + s);
            }

            indexAnswer = 0;

            System.out.println(messageSource.getMessage("label.chooseCorrect", null, locale));
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                System.out.println(messageSource.getMessage("label.incorrectAnswer", null, locale));
            }
            if(questionService.isCorrect(allAnswer.get(i).get(answer), allCorrectAnswer.get(i))) {
                countCorrectAnswer++;
            }

        }
        System.out.println(messageSource.getMessage("label.correctAnswer", null, locale) + " " + countCorrectAnswer + "/5");

    }

    private void greeting() {
        locale = "ru".equals(System.getProperty("user.language")) ? new Locale("ru", "RU") : Locale.ENGLISH;

        System.out.println(messageSource.getMessage("label.enterFirstName", null, locale));
        String name = scanner.nextLine();
        System.out.println(messageSource.getMessage("label.enterSecondName", null, locale));
        String secondName = scanner.nextLine();
        System.out.println(messageSource.getMessage("label.hello", null, locale)
                + " "
                + userService.createUser(name, secondName)
                + " "
                + messageSource.getMessage("label.testBegin", null, locale));
    }
}
