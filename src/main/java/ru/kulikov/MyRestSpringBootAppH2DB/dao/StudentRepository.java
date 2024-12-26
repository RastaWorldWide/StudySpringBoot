package ru.kulikov.MyRestSpringBootAppH2DB.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kulikov.MyRestSpringBootAppH2DB.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}