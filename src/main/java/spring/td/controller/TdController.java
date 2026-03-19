package spring.td.controller;

import org.springframework.web.bind.annotation.*;
import spring.td.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TdController {
    @GetMapping("/welcome")
    String sayWelcome(@RequestParam String name) {
        return "Welcome " + name;
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
}
