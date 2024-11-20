package ru.kulikov.MySecondTestAppSpringBoot.service;

import ru.kulikov.MySecondTestAppSpringBoot.model.Positions;

public interface QuarterlyBonusService {

    double calculate(boolean isManager, Positions position, double salary, double bonus, int workDays, int year, int quarter);
}
