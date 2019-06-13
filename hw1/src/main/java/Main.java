import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.InputOutputService;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        InputOutputService inputOutputService = (InputOutputService) context.getBean("InputOutputServiceImpl");
        inputOutputService.startExam();
    }
}
