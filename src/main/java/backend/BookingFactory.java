package backend;

import backend.entities.Booking;
import backend.entities.Customer;
import backend.entities.Restaurant;


import java.time.LocalDateTime;

public class BookingFactory {

        public Booking createBooking(
            Restaurant restaurant,
            Customer customer,
            Long tableID,
            LocalDateTime start,
            LocalDateTime end) {

            if (restaurant == null)
                throw new IllegalArgumentException("Restaurant required");

            if (customer == null)
                throw new IllegalArgumentException("Customer required");

            if (tableID == null)
                throw new IllegalArgumentException("Table required");

            if (start == null || end == null || !start.isBefore(end))
                throw new IllegalArgumentException("Invalid time");

            Booking booking = new Booking(restaurant, customer, tableID, start, end);

            return booking;

        }
}
