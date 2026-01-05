package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.entities.DiningTable;
import org.example.entities.Restaurant;

import java.util.List;

public class DiningTableRepo {
    EntityManager em;

    public DiningTableRepo(EntityManager em) {
        this.em = em;
    }


    public List<DiningTable> fetchTablesByRestaurant(Restaurant restaurant) {
        return em.createQuery("""
            SELECT t FROM DiningTable t
            WHERE t.restaurant = :restaurant
            ORDER BY t.tableNumber
        """, DiningTable.class)
            .setParameter("restaurant", restaurant)
            .getResultList();
    }

    public List<DiningTable> fetchByRestaurantAndCapacity(
        Restaurant restaurant, int numberOfGuests) {

        return em.createQuery("""
            SELECT t FROM DiningTable t
            WHERE t.restaurant = :restaurant
              AND t.capacity >= :numberOfGuests
            ORDER BY t.capacity
        """, DiningTable.class)
            .setParameter("restaurant", restaurant)
            .setParameter("numberOfGuests", numberOfGuests)
            .getResultList();
    }

}
