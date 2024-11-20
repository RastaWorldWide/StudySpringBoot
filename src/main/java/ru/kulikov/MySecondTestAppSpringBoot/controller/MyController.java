package ru.kulikov.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kulikov.MySecondTestAppSpringBoot.exception.*;
import ru.kulikov.MySecondTestAppSpringBoot.model.*;
import ru.kulikov.MySecondTestAppSpringBoot.service.*;
import ru.kulikov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kulikov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.kulikov.MySecondTestAppSpringBoot.model.*;
import ru.kulikov.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.kulikov.MySecondTestAppSpringBoot.service.ValidationService;
import ru.kulikov.MySecondTestAppSpringBoot.service.*;
import ru.kulikov.MySecondTestAppSpringBoot.util.DateTimeUtil;
import ru.kulikov.MySecondTestAppSpringBoot.exception.ValidationFailedException;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService")
                        ModifyResponseService modifyResponseService) {
                        @Qualifier("modifySystemTimeRequestService") ModifyRequestService modifyRequestService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        AnnualBonusService annualBonusService,
                        QuarterlyBonusService quarterlyBonusService) {

        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
        this.annualBonusService = annualBonusService;
        this.quarterlyBonusService = quarterlyBonusService;
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
            if ("123".equals(request.getUid())) {
                log.error("uid is 123, throw UnsupportedException");
                throw new UnsupportedCodeException();
            }

            validationService.isValid(bindingResult);

        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);

            log.info("add response error data: {}", response);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

            return generateErrorResponse(response, ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);

            log.info("add response error data: {}", response);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

            return generateErrorResponse(response, ErrorCodes.UNSUPPORTED_EXCEPTION, ErrorMessages.UNSUPPORTED, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);

            log.info("add response error data: {}", response);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            return generateErrorResponse(response, ErrorCodes.UNKNOWN_EXCEPTION, ErrorMessages.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setAnnualBonus(annualBonusService.calculate(request.getPosition(), request.getSalary(),
                request.getBonus(), request.getWorkDays(), Year.now().getValue()));

        if (request.isManager()) {
            response.setQuarterlyBonus(quarterlyBonusService.calculate(true, request.getPosition(),
                    request.getSalary(), request.getBonus(), request.getWorkDays(), Year.now().getValue(), getCurrentQuarter()));
        }
        modifyRequestService.modify(request);
        return ResponseEntity.ok(modifyResponseService.modify(response));
    }
    private ResponseEntity<Response> generateErrorResponse(Response response, ErrorCodes code, ErrorMessages message,
                                                           HttpStatus status) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(code);
        response.setErrorMessage(message);
        log.info("response error: {}", response);
        return new ResponseEntity<>(response, status);
    }

    private int getCurrentQuarter() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int monthValue = calendar.get(Calendar.MONTH);
        return monthValue / 3;
    }
}