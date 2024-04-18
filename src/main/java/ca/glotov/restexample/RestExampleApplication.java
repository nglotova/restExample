package ca.glotov.restexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ca.glotov.restexample.todolist.model", "ca.glotov.restexample.todolist.service", "ca.glotov.restexample.todolist.dao"})
public class RestExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestExampleApplication.class, args);
    }
}
