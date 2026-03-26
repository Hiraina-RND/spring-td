package spring.td.service;

import org.springframework.stereotype.Service;
import spring.td.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class TdService {
    private final List<StudentEntity> students = new ArrayList<>();

    public List<StudentEntity> saveStudents(List<StudentEntity> newStudents) {
        students.addAll(newStudents);
        return students;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }
}
