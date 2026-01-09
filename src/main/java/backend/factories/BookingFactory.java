package backend.factories;

import backend.entities.Booking;
import backend.entities.Customer;
import backend.entities.DiningTable;
import backend.entities.Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

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

            Booking booking = new Booking(restaurant, customer, tableId, start, end,  date);

            return booking;
        }
    }

