package ru.kulikov.SpringH2Base.service;

import org.springframework.stereotype.Service;
import ru.kulikov.SpringH2Base.entity.Student;

import java.util.List;
@Service
public interface StudentService {

    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudent(int id);

    void deleteStudent(int id);
}