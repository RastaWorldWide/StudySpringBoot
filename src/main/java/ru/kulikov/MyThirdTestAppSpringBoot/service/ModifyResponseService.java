package ru.kulikov.MyThirdTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kulikov.MyThirdTestAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);
}
