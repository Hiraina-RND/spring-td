package spring.td.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TdController {
    @GetMapping("/welcome")
    String sayWelcome(@RequestParam String name) {
        return "Welcome " + name;
    }
}
