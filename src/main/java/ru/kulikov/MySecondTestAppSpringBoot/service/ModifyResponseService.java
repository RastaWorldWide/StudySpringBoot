package ru.kulikov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kulikov.MySecondTestAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);
}
