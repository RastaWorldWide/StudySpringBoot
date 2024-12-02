package ru.kulikov.MyRestSpringBootAppH2DB.dao;

import org.springframework.stereotype.Repository;
import ru.kulikov.MyRestSpringBootAppH2DB.entity.Course;
import java.util.List;
@Repository
public interface CourseDAO {
    List<Course> getAllCourses();

    Course saveCourse(Course course);

    Course getCourse(int id);

    void deleteCourse(int id);
}
