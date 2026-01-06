package org.example.repositories;
import jakarta.persistence.EntityManager;
import org.example.entities.*;
import java.time.LocalDateTime;
import java.util.List;

public class BookingRepo {
    EntityManager em;

    public BookingRepo(EntityManager em) {
        this.em = em;
    }

    public List<Booking> fetchBookingsByRestaurant(Restaurant restaurant) {
        return em.createQuery("""
        SELECT b FROM Booking b
        WHERE b.restaurant_id = :restaurantId
        ORDER BY b.booking_start
        """, Booking.class)
            .setParameter("restaurantId", restaurant.getId())
            .getResultList();
    }


    public List<Booking> fetchConflictingBookings(
        DiningTable table,
        LocalDateTime start,
        LocalDateTime end) {

        return em.createQuery("""
            SELECT b FROM Booking b
            JOIN b.diningTables t
            WHERE t = :table
              AND b.startTime < :end
              AND b.endTime   > :start
        """, Booking.class)
            .setParameter("table", table)
            .setParameter("start", start)
            .setParameter("end", end)
            .getResultList();
        }

    public List<Booking> fetchBookingsByCustomer(Customer customer) {
        return em.createQuery("""
        SELECT  b FROM Booking b
        WHERE b.customer = :customer
        ORDER BY b.booking_start
        """, Booking.class)
            .setParameter("customer", customer)
            .getResultList();


    }
}
