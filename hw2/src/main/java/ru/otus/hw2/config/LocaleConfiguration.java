package ru.otus.hw2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Locale;

@Configuration
@PropertySource("classpath:application.properties")
public class LocaleConfiguration {
    @Bean
    public Locale locale() {
        return "ru".equals(System.getProperty("user.language")) ? new Locale("ru", "RU") : Locale.ENGLISH;
    }
}
