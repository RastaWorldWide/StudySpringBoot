package ru.kulikov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kulikov.MySecondTestAppSpringBoot.model.Request;
@Service
public interface ModifyRequestService {

    void modify(Request request);
}
