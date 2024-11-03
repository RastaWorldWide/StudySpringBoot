package ru.kulikov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.kulikov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kulikov.MySecondTestAppSpringBoot.exception.ValidationFailedException;

@Service
public interface ValidationService {
<<<<<<< HEAD
    void isValid(BindingResult bindingResult) throws ValidationFailedException, UnsupportedCodeException;;
=======
    void isValid(BindingResult bindingResult) throws ValidationFailedException, UnsupportedCodeException;
>>>>>>> 2610f4407f3d5f6ad1e6552044f510be2ad493c9
}