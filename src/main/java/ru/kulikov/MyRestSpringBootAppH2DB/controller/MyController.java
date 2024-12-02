package ru.kulikov.MyRestSpringBootAppH2DB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kulikov.MyRestSpringBootAppH2DB.service.StudentService;
import ru.kulikov.MyRestSpringBootAppH2DB.entity.Student;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> showAllStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        return allStudents;
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable("id") int id) {
        return studentService.getStudent(id);
    }

    @PostMapping("/students")
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        if (savedStudent != null) {
            return new ResponseEntity<>("Student saved successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to save student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/students")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.saveStudent(student);
        if (updatedStudent != null) {
            return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/students/{id}")
    public void updateStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
    }
}