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
        String restaurantName, int numberOfGuests) {
        return callInTransaction(em ->
            em.createQuery("""
            SELECT t FROM DiningTable t
                JOIN t.restaurant r
            WHERE r.name = :restaurantName
              AND t.seatCapacity >= :numberOfGuests
            ORDER BY t.seatCapacity
        """, DiningTable.class)
                .setParameter("restaurantName", restaurantName)
                .setParameter("numberOfGuests", numberOfGuests)
                .getResultList());
    }



}
