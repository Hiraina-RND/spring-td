package spring.td.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.td.entity.StudentEntity;
import spring.td.service.TdService;
import spring.td.validator.TdValidator;

import java.util.List;

@RestController
public class TdController {
    private final TdService tdService;
    private final TdValidator tdValidator;

    public TdController(
            TdService studentService,
            TdValidator tdValidator, TdService tdService
    ) {
        this.tdService = tdService;
        this.tdValidator = tdValidator;
    }

    @GetMapping("/welcome")
    ResponseEntity<String> sayWelcome(@RequestParam(required = false) String name) {
        try {
            tdValidator.validateName(name);
        } catch (BadRequestException e) {
            return ResponseEntity
                    .status(400)
                    .body(e.getMessage());
        }
        return ResponseEntity
                .status(200)
                .body("Welcome " + name);
    }

    @PostMapping("/students")
    ResponseEntity<?> saveStudents(@RequestBody List<StudentEntity> newStudents) {
        try {
            tdValidator.validateStudents(newStudents);
        } catch (BadRequestException e) {
            return ResponseEntity
                    .status(400)
                    .body(e.getMessage());
        }
        return ResponseEntity
                .status(201)
                .body(tdService.saveStudents(newStudents));
    }

    @GetMapping(value = "/students", produces = {"application/json", "text/plain"})
    ResponseEntity<?> getStudents(@RequestHeader(required = false) String accept) {
        try {
            tdValidator.validateAcceptHeader(accept);
        } catch (BadRequestException e) {
            return ResponseEntity
                    .status(400)
                    .body(e.getMessage());
        }
        return ResponseEntity
                .status(200)
                .body(tdService.getStudents());
    }
}
