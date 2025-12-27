package org.example.repositories;

import org.example.entities.DiningTable;
import java.util.List;

public interface DiningTableRepo {

    void save(DiningTable table);

    DiningTable findById(Long id);

    List<DiningTable> findAll();

    void update(DiningTable table);

    void delete(DiningTable table);
}