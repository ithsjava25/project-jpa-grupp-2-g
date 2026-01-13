package backend.services;

import backend.entities.Booking;
import backend.entities.DiningTable;
import backend.entities.Restaurant;
import backend.factories.CustomerFactory;
import backend.repositories.DiningTableRepo;
import backend.repositories.RestaurantRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookingServiceTest {

    private BookingService bookingService;
    private Restaurant restaurant;


    @BeforeEach
    void setup() {
        bookingService = new BookingService();
        restaurant = new Restaurant();
        new RestaurantRepo().add(restaurant);
        new DiningTableRepo().add(new DiningTable(1,4,restaurant));
    }

    @Test
    void createBookingWhenTableIsAvailable() {
        Booking booking = bookingService.bookTable(
            restaurant,
            "Test",
            "Testsson",
            "test@gmail.com",
            "0712345678",
            2,
            LocalTime.of(18,0),
            LocalTime.of(20,0),
            LocalDate.now().plusDays(1)
        );
        assertNotNull(booking);
    }

    @Test
    void createBookingWhenNoTableCapacity() {
        assertThrows(IllegalStateException.class, () ->
            bookingService.bookTable(
                restaurant,
                "Test",
                "Testsson",
                "test@gmail.com",
                "0712345678",
                10,
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalDate.now().plusDays(1))
            );

    }

    @Test
    void shouldNotAllowDoubleBookingSameTime() {

        bookingService.bookTable(
            restaurant,
            "Anna",
            "Andersson",
            "anna@test.se",
            "0701111111",
            2,
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            LocalDate.now()
        );
        assertThrows(IllegalStateException.class, () ->
            bookingService.bookTable(
                restaurant,
                "Bob",
                "Bengtsson",
                "bob@test.se",
                "0712345671",
                2,
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalDate.now()
            )
        );
    }
    @Test
    void shouldNotAllowOverlappingBookings() {

        bookingService.bookTable(
            restaurant,
            "Test",
            "Testsson",
            "test@gmail.com",
            "0712345671",
            2,
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            LocalDate.now()
        );
        assertThrows(IllegalStateException.class, () ->
            bookingService.bookTable(
                restaurant,
                "Bob",
                "Bengtsson",
                "bob@test.se",
                "0712345672",
                2,
                LocalTime.of(19, 0),
                LocalTime.of(21, 0),
                LocalDate.now()
            )
        );
    }
}


