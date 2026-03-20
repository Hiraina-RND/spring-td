package spring.td.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.td.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;

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
    ResponseEntity<?> saveStudents(@RequestBody List<StudentEntity> newStudents) {
        try {
            studentsList.addAll(newStudents);

            return ResponseEntity
                    .status(201)
                    .body(studentsList);
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body("Internal server error: " + e.getMessage());
        }
    }

    @GetMapping("/students")
    ResponseEntity<?> getStudents(@RequestHeader(required = false) String accept) {
        try {
            if (accept == null || accept.isEmpty()) {
                return ResponseEntity
                        .status(400)
                        .body("Missing Accept header");
            }

            if (accept.equals("text/plain") || accept.equals("application/json")) {
                return ResponseEntity
                        .status(200)
                        .body(studentsList);
            }
            return ResponseEntity
                    .status(501)
                    .body("Invalid Accept header");
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body("Internal server error: " + e.getMessage());
        }
    }
}
