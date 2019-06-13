package ru.otus.hw2.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class QuestionDaoConfiguration {
    @Bean
    public QuestionDaoImpl questionDaoImpl(@Value("${questionRU}") String pathRu, @Value("${questionEN}") String pathEn){
        if ("ru".equals(System.getProperty("user.language"))) {
            return new QuestionDaoImpl(pathRu);
        } else {
            return new QuestionDaoImpl(pathEn);
        }
    }
}
