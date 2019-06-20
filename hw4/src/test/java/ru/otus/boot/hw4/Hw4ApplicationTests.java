package ru.otus.boot.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.boot.hw4.dao.QuestionDao;
import ru.otus.boot.hw4.service.QuestionService;
import ru.otus.boot.hw4.service.QuestionServiceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class Hw4ApplicationTests {

    private QuestionDao questionDao = mock(QuestionDao.class);
    private QuestionService questionService = new QuestionServiceImpl(questionDao);

    @Test
    @DisplayName("getAllMessageMixedAnswer")
    void getAllAnswer() {
        Map<Integer, List<String>> testData = new HashMap<>();
        testData.put(0, Arrays.asList("corr0", "incor1", "incor2, incor3, incor4"));
        testData.put(1, Arrays.asList("corr0", "incor1", "incor2, incor3, incor4"));
        testData.put(2, Arrays.asList("corr0", "incor1", "incor2, incor3, incor4"));

        when(questionDao.findAllIAnswers()).thenReturn(testData);

        Map<Integer, List<String>> allAnswer = questionService.getAllAnswer();

        assertThat(allAnswer).hasSameSizeAs(testData);

        assertThat(allAnswer.get(0)).containsExactlyInAnyOrder("corr0", "incor1", "incor2, incor3, incor4");
        assertThat(allAnswer.get(1)).containsExactlyInAnyOrder("corr0", "incor1", "incor2, incor3, incor4");
        assertThat(allAnswer.get(2)).containsExactlyInAnyOrder("corr0", "incor1", "incor2, incor3, incor4");
    }

    @Test
    @DisplayName("checkCorrectAnswerShouldReturnTrue")
    void testCorrectTrue() {
        assertThat(questionService.isCorrect("aaa", "aaa")).isTrue();
    }

    @Test
    @DisplayName("checkCorrectAnswerShouldReturnFalse")
    void testCorrectFalse() {
        assertThat(questionService.isCorrect("aaa", "bbb")).isFalse();
    }

}
