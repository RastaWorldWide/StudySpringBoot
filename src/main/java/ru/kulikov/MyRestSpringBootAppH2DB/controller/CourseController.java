package ru.kulikov.MyRestSpringBootAppH2DB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kulikov.MyRestSpringBootAppH2DB.service.CourseService;
import ru.kulikov.MyRestSpringBootAppH2DB.entity.Course;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable("id") int id) {
        return courseService.getCourse(id);
    }

    @PostMapping
    public Course saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") int id) {
        courseService.deleteCourse(id);
    }
}
