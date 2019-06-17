package ru.otus.hw2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.hw2.service.InputOutputService;
import ru.otus.hw2.service.InputOutputServiceImpl;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        InputOutputService inputOutputService = context.getBean("inputOutputServiceImpl", InputOutputServiceImpl.class);
        inputOutputService.startExam();
    }
}
