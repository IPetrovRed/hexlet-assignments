package exercise.controller;

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
@RestController
@RequestMapping("/welcome")

public class WelcomeController {
    private final Daytime daytime;
    @Autowired
    public WelcomeController(Daytime daytime) {
        this.daytime = daytime;
    }

    @GetMapping(path = "")
    public String welcome() {
        return "It is " + daytime.getName() + " now! Welcome to Spring!";
    }
}
// END
