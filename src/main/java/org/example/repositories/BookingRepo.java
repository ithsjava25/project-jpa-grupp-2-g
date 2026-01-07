package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.ConnectionProvider;
import org.example.entities.Booking;
import org.example.entities.Customer;
import org.example.entities.DiningTable;
import org.example.entities.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

public class BookingRepo extends BaseRepo<Booking> {

    public BookingRepo(){
        super(Booking.class);
    }

    public List<Booking> fetchBookingsByRestaurant(Restaurant restaurant) {
        return callInTransaction(em ->
            em.createQuery("""
                SELECT b FROM Booking b
                WHERE b.restaurant = :restaurant
                ORDER BY b.bookingStart
            """, Booking.class)
                .setParameter("restaurant", restaurant)
                .getResultList());

    }


    public List<Booking> fetchConflictingBookings(
        DiningTable table,
        LocalDateTime start,
        LocalDateTime end) {
            return callInTransaction(em ->
                em.createQuery("""
                SELECT b FROM Booking b
                JOIN b.tables t
                WHERE t = :table
                  AND b.bookingStart < :end
                  AND b.bookingEnd   > :start
            """, Booking.class)
                .setParameter("table", table)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList());

    }

    public List<Booking> fetchBookingsByCustomer(Customer customer) {
        return callInTransaction(em ->
            em.createQuery("""
                SELECT b FROM Booking b
                WHERE b.customer = :customer
                ORDER BY b.bookingStart
            """, Booking.class)
                .setParameter("customer", customer)
                .getResultList());
    }
}
