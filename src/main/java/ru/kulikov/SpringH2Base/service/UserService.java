package ru.kulikov.SpringH2Base.service;


import ru.kulikov.SpringH2Base.dto.UserDto;
import ru.kulikov.SpringH2Base.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}