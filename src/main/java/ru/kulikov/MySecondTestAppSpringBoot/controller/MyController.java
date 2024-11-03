package ru.kulikov.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
<<<<<<< HEAD
=======
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
>>>>>>> 2610f4407f3d5f6ad1e6552044f510be2ad493c9
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kulikov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kulikov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
<<<<<<< HEAD
import ru.kulikov.MySecondTestAppSpringBoot.model.Request;
import ru.kulikov.MySecondTestAppSpringBoot.model.Response;
import ru.kulikov.MySecondTestAppSpringBoot.service.ValidationService;


=======
import ru.kulikov.MySecondTestAppSpringBoot.model.*;
import ru.kulikov.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.kulikov.MySecondTestAppSpringBoot.service.ValidationService;
import ru.kulikov.MySecondTestAppSpringBoot.util.DateTimeUtil;

import java.util.Date;

@Slf4j
>>>>>>> 2610f4407f3d5f6ad1e6552044f510be2ad493c9
@RestController
public class MyController {

    private final ValidationService validationService;
<<<<<<< HEAD

    @Autowired
    public MyController(ValidationService validationService) {

        this.validationService = validationService;

    }
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody @Valid Request request, BindingResult bindingResult) {
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();

        try {
            if ("123".equals(request.getUid())) {
                throw new UnsupportedCodeException();
            }


            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder("Ошибки валидации: ");
                bindingResult.getFieldErrors().forEach(error -> {
                    errorMessage.append(String.format("[%s: %s] ", error.getField(), error.getDefaultMessage()));
                });
                throw new ValidationFailedException(errorMessage.toString());
            }

        } catch (ValidationFailedException e) {
            response.setCode("failed");
            response.setErrorCode("ValidationException");
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        } catch (UnsupportedCodeException e) {
            response.setCode("failed");
            response.setErrorCode("UnsupportedCodeException");
            response.setErrorMessage("UID равен 123, что не поддерживается");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            response.setCode("failed");
            response.setErrorCode("UnknownException");
            response.setErrorMessage("Произошла непредвиденная ошибка");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
=======
    private final ModifyResponseService modifyResponseService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService")
                        ModifyResponseService modifyResponseService) {

        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
    }
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody @Valid Request request, BindingResult bindingResult) {

        log.info("request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        log.info("response created: {}", response);

        try {
            validationService.isValid(bindingResult);

        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);

            log.info("add response error data: {}", response);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        } catch (UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);

            log.info("add response error data: {}", response);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);

            log.info("add response error data: {}", response);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(modifyResponseService.modify(response));
    }
}
>>>>>>> 2610f4407f3d5f6ad1e6552044f510be2ad493c9
