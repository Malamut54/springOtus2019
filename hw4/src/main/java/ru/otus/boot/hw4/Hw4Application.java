package ru.otus.boot.hw4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.boot.hw4.service.InputOutputServiceImpl;

@SpringBootApplication
public class Hw4Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Hw4Application.class, args);
        context.getBean("inputOutputServiceImpl", InputOutputServiceImpl.class).startExam();
    }

}
