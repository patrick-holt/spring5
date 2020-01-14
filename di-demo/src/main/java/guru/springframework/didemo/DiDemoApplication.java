package guru.springframework.didemo;

import guru.springframework.didemo.controllers.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DiDemoApplication {

    public static void main(String[] args) {
        // The run method will return is an application context we can now handle
        ApplicationContext ctx = SpringApplication.run(DiDemoApplication.class, args);

        // Get a hold of Spring bean and find it by the name (Spring uses camelCase)
        MyController controller = (MyController) ctx.getBean("myController");

        // getBean not strongly casted, cast it ourselves
        controller.hello();
    }

}
