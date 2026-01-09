package backend.repositories;

import backend.entities.Booking;
import backend.entities.Customer;
import backend.entities.DiningTable;
import backend.entities.Restaurant;

import java.time.LocalDate;
import java.time.LocalTime;
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
        LocalTime start,
        LocalTime end) {
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
//
    public List<Booking> fetchAllBookingsByTableIdAndDate(
        Long tableId, LocalDate date) {
        return callInTransaction(em ->
            em.createQuery("""
            SELECT b FROM Booking b
            WHERE b.tableId = :tableId
            AND b.date = :date
        """, Booking.class)
                .setParameter("tableId", tableId)
                .setParameter("date", date)
                .getResultList());
    }
}
