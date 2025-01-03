package ru.kulikov.SpringH2Base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kulikov.SpringH2Base.entity.Role;
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}