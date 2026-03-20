package spring.td.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.td.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TdController {
    @GetMapping("/welcome")
    ResponseEntity<String> sayWelcome(@RequestParam(required = false) String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity
                    .status(400)
                    .body("Parameter 'name' is required and must not be empty");
        }
        return ResponseEntity
                .status(200)
                .body("Welcome " + name);
    }

    List<StudentEntity> studentsList = new ArrayList<>();
    @PostMapping("/students")
    String saveStudents(@RequestBody List<StudentEntity> newStudents) {
        studentsList.addAll(newStudents);
        return studentsList
                .stream()
                .map(studentEntity -> studentEntity.getFirstName() + " " + studentEntity.getLastName())
                .collect(Collectors.joining(", "));
    }

    @GetMapping("/students")
    String getStudents(@RequestHeader(value = "Accept", defaultValue = "text/plain") String accept) {
        System.out.println(accept);
        if (!accept.equals("text/plain")) {
            return "Format non supporté";
        }

        return studentsList
                .stream()
                .map(studentEntity -> studentEntity.getFirstName() + " " + studentEntity.getLastName())
                .collect(Collectors.joining(", "));
    }
}
