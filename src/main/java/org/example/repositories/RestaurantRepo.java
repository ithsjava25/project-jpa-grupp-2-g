package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.entities.Restaurant;

import java.util.List;

public class RestaurantRepo {
    EntityManager em;

    public RestaurantRepo(EntityManager em) {
        this.em = em;
    }

    public Restaurant fetchRestaurantByName(String name) {
        return em.createQuery("""
            SELECT r FROM Restaurant r
            WHERE r.name = :name
            """, Restaurant.class)
            .setParameter("name", name)
            .getSingleResultOrNull();
    }
}
