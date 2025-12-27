package org.example.repositories;

import org.example.entities.OpeningHours;
import java.util.List;

public interface OpeningHoursInter {

    void save(OpeningHours openingHours);

    OpeningHours findById(Long id);

    List<OpeningHours> findAll();

    void update(OpeningHours openingHours);

    void delete(OpeningHours openingHours);
}