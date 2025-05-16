import manager.ApplicationContext;
import service.NotificationService;


public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ApplicationContext();

        applicationContext.reflectionComponent(Class.forName("service.EmailService"));
        applicationContext.reflectionComponent(Class.forName("service.SMSService"));
        applicationContext.reflectionComponent(Class.forName("service.NotificationService"));

        NotificationService service = (NotificationService) applicationContext.getBean("NotificationService");

        service.send("Hi");
    }
}