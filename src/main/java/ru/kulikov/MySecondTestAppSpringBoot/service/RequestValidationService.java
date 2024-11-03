package ru.kulikov.MySecondTestAppSpringBoot.service;

<<<<<<< HEAD
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.kulikov.MySecondTestAppSpringBoot.exception.ValidationFailedException;

=======
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.kulikov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kulikov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
@Slf4j
>>>>>>> 2610f4407f3d5f6ad1e6552044f510be2ad493c9
@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException{
        if (bindingResult.hasErrors()) {
<<<<<<< HEAD
            throw new
                    ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }
}
=======
            StringBuilder errorMessage = new StringBuilder("Ошибки валидации: ");
            bindingResult.getFieldErrors().forEach(error -> {
                errorMessage.append(String.format("[%s: %s] ", error.getField(), error.getDefaultMessage()));
            });

            log.error("bindingResult has errors, throw ValidationFailedException");
            throw new ValidationFailedException(errorMessage.toString());
        }
    }
}
>>>>>>> 2610f4407f3d5f6ad1e6552044f510be2ad493c9
