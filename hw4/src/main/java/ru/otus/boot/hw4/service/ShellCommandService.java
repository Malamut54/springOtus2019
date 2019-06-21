package ru.otus.boot.hw4.service;

import org.springframework.context.MessageSource;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.boot.hw4.model.User;

import java.util.Locale;

@ShellComponent
public class ShellCommandService {
    private final UserService userService;
    private final InputOutputService inputOutputService;
    private final Locale locale;
    private final MessageSource messageSource;
    private User user;

    public ShellCommandService(UserService userService, InputOutputService inputOutputService,
                               Locale locale, MessageSource messageSource) {
        this.userService = userService;
        this.inputOutputService = inputOutputService;
        this.locale = locale;
        this.messageSource = messageSource;
    }

    @ShellMethod("Fill First Name and Second Name")
    public String greeting(String fname, String sname) {
        user = userService.createUser(fname, sname);
        return messageSource.getMessage("label.hello", null, locale)
                + " "
                + user.toString()
                + " "
                + messageSource.getMessage("label.testBegin", null, locale);
    }

    @ShellMethod("Start Exam")
    public void startExam() {
        if (user == null) {
            throw new RuntimeException(messageSource.getMessage("label.userNotSet", null, locale));
        } else {
            inputOutputService.startExam();
        }
    }

}
