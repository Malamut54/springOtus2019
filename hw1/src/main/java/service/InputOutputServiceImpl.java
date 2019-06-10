package service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputOutputServiceImpl implements InputOutputService {
    private QuestionService questionService;
    private UserService userService;
    private Scanner scanner = new Scanner(System.in);

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
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

        for (int i = 0; i < 5; i++) {
            System.out.println(allQuestion.get(i));

            for (String s : allAnswer.get(i)) {
                System.out.println(indexAnswer++ + " " + s);
            }

            indexAnswer = 0;

            System.out.println("Choose correct Answer");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Only digit");
            }
            if(questionService.isCorrect(allAnswer.get(i).get(answer), allCorrectAnswer.get(i))) {
                countCorrectAnswer++;
            }

        }
        System.out.println("Correct Answer" + countCorrectAnswer + "/5");

    }
    private void greeting() {
        System.out.println("Enter you name");
        String name = scanner.nextLine();
        System.out.println("Enter your second Name");
        String secondName = scanner.nextLine();

        System.out.println("Hello" + " " + userService.createUser(name, secondName) + ", test begin");
    }
}
