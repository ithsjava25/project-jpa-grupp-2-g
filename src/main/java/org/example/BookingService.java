package org.example;

import jakarta.persistence.EntityManager;
import org.example.entities.Booking;
import org.example.entities.Customer;
import org.example.entities.Restaurant;
import org.example.repositories.CustomerRepo;
import org.example.repositories.DiningTableRepo;
import org.example.repositories.RestaurantRepo;

import java.time.LocalDateTime;
import java.util.List;

public class BookingService {
    private EntityManager em;

    public BookingService(EntityManager em) {
        this.em = em;
    }

    private final BookingFactory bookingFactory = new BookingFactory();
    private final RestaurantRepo restaurantRepo = new RestaurantRepo(em);
    private final CustomerRepo customerRepo = new CustomerRepo(em);
    private final DiningTableRepo diningTableRepo = new DiningTableRepo(em);


    public void createBooking(
        String restaurantName,
        String customerFirstName,
        String customerLastName,
        int numberOfGuests,
        LocalDateTime start,
        LocalDateTime end) {



        //Step 1: Validera argumenten.


        //Step 2: Hämta all nödvändig data från databas (repo).
        //List<Restaurant> restaurants = restaurantRepo.fetchRestaurantByName(restaurantName);
        Customer customer = customerRepo.fetchCustomerByFirstNameAndLastName(customerFirstName, customerLastName);

        //Step 3: Hitta tableId (Vilket bord ska jag boka?).


        //Step 4: Skapa bokningen.
        //Booking b = bookingFactory.createBooking(restaurants.get(0), customer, 1l, start, end);
    }
}
