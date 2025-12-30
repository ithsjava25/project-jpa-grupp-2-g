package org.example.repositories;

import java.util.Optional;

public interface RepoInterface <T>{

    /**
     * Add new object to table
     * @param object, can be any entity
     */
    void add(T object);

    /**
     * Update an existing object
     * @param object, can be any entity
     */
    void update(T object);

    /**
     * Deletes an existing object
     * @param object, can be any entity
     */
    void delete(T object);

}
