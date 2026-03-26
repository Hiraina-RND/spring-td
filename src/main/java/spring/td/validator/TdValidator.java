package spring.td.validator;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;
import spring.td.entity.StudentEntity;

import java.util.List;

@Component
public class TdValidator {
    public void validateStudents(List<StudentEntity> students) throws BadRequestException {
        if (students == null || students.isEmpty()) {
            throw new BadRequestException("Students list can not be empty");
        }

        for (StudentEntity student : students) {
            if (student.getReference() == null || student.getReference().isBlank()) {
                throw new BadRequestException("Student reference is required");
            }
            if (student.getFirstName() == null || student.getFirstName().isBlank()) {
                throw new BadRequestException("Student firstName is required");
            }
            if (student.getLastName() == null || student.getLastName().isBlank()) {
                throw new BadRequestException("Student lastName is required");
            }
        }
    }

    public void validateName(String name) throws BadRequestException {
        if (name == null || name.trim().isEmpty()) {
            throw new BadRequestException("Parameter 'name' is required");
        }
    }

    public void validateAcceptHeader(String accept) throws BadRequestException {
        if (accept == null || accept.isEmpty()) {
            throw new BadRequestException("Missing Accept header");
        }

        if (!accept.equals("application/json") && !accept.equals("text/plain")) {
            throw new BadRequestException("Invalid Accept header");
        }
    }
}
