package backend.services;

import backend.entities.Booking;
import backend.entities.Restaurant;
import backend.repositories.BookingRepo;
import backend.repositories.RestaurantRepo;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingServiceTest {

    private final BookingService bookingService =  new BookingService();

    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:9.5.0")
        .withDatabaseName("testdb")
        .withUsername("user")
        .withPassword("password")
        .withConfigurationOverride("myconfig")
        .withInitScript("testData.sql")
        .withCreateContainerCmdModifier(cmd ->
            cmd.getHostConfig().withPortBindings(
                new PortBinding(
                    Ports.Binding.bindPort(3307),   // HOST PORT
                    new ExposedPort(3306)           // CONTAINER PORT
                )
            )
        );;

    @BeforeAll
    static void wireDbProperties() {
        System.setProperty("APP_JDBC_URL", mysql.getJdbcUrl());
        System.out.println("mysql url: " + mysql.getJdbcUrl());
        System.setProperty("APP_DB_USER", mysql.getUsername());
        System.setProperty("APP_DB_PASS", mysql.getPassword());

    }

    @Test
    @Order(0)
    void testConnection() throws SQLException {

        try (Connection conn = DriverManager.getConnection(
            mysql.getJdbcUrl(),
            mysql.getUsername(),
            mysql.getPassword());
             Statement s = conn.createStatement()) {

            assertThat(conn).isNotNull();

            s.execute("SET FOREIGN_KEY_CHECKS=0");
            s.execute("TRUNCATE TABLE Booking");
            s.execute("SET FOREIGN_KEY_CHECKS=1");

        }
    }

    @Test
    @Order(1)
    void createBookingWhenTableIsAvailable() {
        RestaurantRepo restaurantRepo = new RestaurantRepo();
        BookingRepo bookingRepo = new BookingRepo();
        Restaurant restaurant = restaurantRepo.fetchRestaurantByName("TOSO");

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
        List<Booking> fetchedBookings = bookingRepo.fetchBookingsByRestaurant(restaurant);
        assertEquals(1, fetchedBookings.size());
        assertTrue(booking.equals(fetchedBookings.getFirst()));

    }

    @Test
    @Order(2)
    void shouldNotAllowBookingWhenNoTableCapacity() {
        RestaurantRepo restaurantRepo = new RestaurantRepo();
        BookingRepo bookingRepo = new BookingRepo();
        Restaurant restaurant = restaurantRepo.fetchRestaurantByName("TOSO");
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
    @Order(3)
    void shouldNotAllowDoubleBookingSameTime() {
        RestaurantRepo restaurantRepo = new RestaurantRepo();
        BookingRepo bookingRepo = new BookingRepo();
        Restaurant restaurant = restaurantRepo.fetchRestaurantByName("TOSO");
        bookingService.bookTable(
            restaurant,
            "Test",
            "Testsson",
            "test@gmail.com",
            "0712345678",
            8,
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            LocalDate.now()
        );
        assertThrows(IllegalStateException.class, () ->
            bookingService.bookTable(
                restaurant,
                "Test",
                "Testsson",
                "test@gmail.com",
                "0712345678",
                8,
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalDate.now()
            )
        );
    }
    @Test
    @Order(4)
    void shouldNotAllowOverlappingBookings() {
        RestaurantRepo restaurantRepo = new RestaurantRepo();
        BookingRepo bookingRepo = new BookingRepo();
        Restaurant restaurant = restaurantRepo.fetchRestaurantByName("Lilla Taverna");
        bookingService.bookTable(
            restaurant,
            "Test",
            "Testsson",
            "test@gmail.com",
            "0712345678",
            8,
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            LocalDate.now()
        );
        assertThrows(IllegalStateException.class, () ->
            bookingService.bookTable(
                restaurant,
                "Test",
                "Testsson",
                "test@gmail.com",
                "0712345678",
                8,
                LocalTime.of(19, 0),
                LocalTime.of(21, 0),
                LocalDate.now()
            )
        );
    }
}


