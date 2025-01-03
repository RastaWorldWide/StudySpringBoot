package ru.kulikov.SpringH2Base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kulikov.SpringH2Base.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
