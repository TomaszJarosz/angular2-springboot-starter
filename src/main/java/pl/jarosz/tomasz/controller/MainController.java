package pl.jarosz.tomasz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.jarosz.tomasz.hello.Greeting;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Tomek on 04.02.2017.
 */
@RestController
public class MainController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
