package ru.otus.boot.hw3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.boot.hw3.service.InputOutputServiceImpl;

@SpringBootApplication
public class Hw3Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Hw3Application.class, args);
        context.getBean("inputOutputServiceImpl", InputOutputServiceImpl.class).startExam();
    }

}
