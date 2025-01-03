package ru.kulikov.SpringH2Base.service;

import org.springframework.stereotype.Service;
import ru.kulikov.SpringH2Base.entity.Discipline;

import java.util.List;

@Service
public interface DisciplineService {

    List<Discipline> getAllDisciplines();

    Discipline saveDiscipline(Discipline discipline);

    Discipline getDiscipline(int id);

    void deleteDiscipline(int id);
}