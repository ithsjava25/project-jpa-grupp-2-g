package backend.repositories;

import backend.entities.DiningTable;
import backend.entities.Restaurant;

import java.util.List;

public class DiningTableRepo extends BaseRepo<DiningTable> {
    public DiningTableRepo() {
        super(DiningTable.class);
    }

    public List<DiningTable> fetchTablesByRestaurant(Restaurant restaurant) {
        return callInTransaction(em ->
            em.createQuery("""
            SELECT t FROM DiningTable t
            WHERE t.restaurant = :restaurant
            ORDER BY t.tableNumber
        """, DiningTable.class)
                .setParameter("restaurant", restaurant)
                .getResultList());

    }

    public List<DiningTable> fetchByRestaurantAndCapacity(
        Restaurant restaurant, int numberOfGuests) {
        return callInTransaction(em ->
            em.createQuery("""
            SELECT t FROM DiningTable t
            WHERE t.restaurant = :restaurant
              AND t.capacity >= :numberOfGuests
            ORDER BY t.capacity
        """, DiningTable.class)
                .setParameter("restaurant", restaurant)
                .setParameter("numberOfGuests", numberOfGuests)
                .getResultList());
    }

}
