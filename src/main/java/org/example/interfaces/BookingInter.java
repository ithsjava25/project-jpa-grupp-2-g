package org.example.factories;

import org.example.entities.Booking;
import org.example.entities.DiningTable;
import org.example.entities.Customer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class BookingFactory {

    public static Booking createBooking(
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime,
            DiningTable table,
            List<Customer> customers) {

        Booking booking = new Booking();
        booking.setDate(date);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setTable(table);
        booking.setCustomers(customers);
        return booking;
    }
}
