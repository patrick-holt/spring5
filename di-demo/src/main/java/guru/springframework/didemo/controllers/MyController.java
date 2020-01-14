package guru.springframework.didemo.controllers;

import org.springframework.stereotype.Controller;

@Controller // Add this controller to the spring context
public class MyController {

    public void hello() {
        System.out.println("Hello world!");

        // In the course he returns a String value of "foo"
    }

}
