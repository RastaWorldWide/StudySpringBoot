package ru.kulikov.MySecondTestAppSpringBoot.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Response {
    private String uid;
    private String operationUid;
<<<<<<< HEAD
    private String systemTime;
    private String code;
    private String errorCode;
    private String errorMessage;
}
=======
    private Systems systemName;
    private String systemTime;
    private Codes code;
    private ErrorCodes errorCode;
    private ErrorMessages errorMessage;
}

>>>>>>> 2610f4407f3d5f6ad1e6552044f510be2ad493c9
