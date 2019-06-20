package ru.otus.boot.hw4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class LocaleConfiguration {
    @Bean
    public Locale locale() {
        return "ru".equals(System.getProperty("user.language")) ? new Locale("ru", "RU") : Locale.ENGLISH;
    }
}
