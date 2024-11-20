package ru.kulikov.MySecondTestAppSpringBoot.service;

import ru.kulikov.MySecondTestAppSpringBoot.model.Positions;

public interface AnnualBonusService {

    double calculate(Positions position, double salary, double bonus, int workDays, int year);

}
