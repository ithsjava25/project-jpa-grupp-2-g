package backend.factories;

import backend.entities.Booking;
import backend.entities.Customer;
import backend.entities.Restaurant;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingFactory {

    public Booking createBooking(
        Restaurant restaurant,
        Customer customer,
        Long tableId,
        LocalTime start,
        LocalTime end,
        LocalDate date){

            if (restaurant == null)
                throw new IllegalArgumentException("Restaurant required");

            if (customer == null)
                throw new IllegalArgumentException("Customer required");
            if (start == null || end == null || !start.isBefore(end))
                throw new IllegalArgumentException("Invalid time");
            if (tableId == null)
                throw new IllegalArgumentException("Table id required");
            if (date == null || date.isBefore(LocalDate.now()))
                throw new IllegalArgumentException("Invalid booking date. Date must be today or later");

            Booking booking = new Booking(restaurant, customer, tableId, start, start.plusHours(2),  date);

            return booking;
        }
    }

