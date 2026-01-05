package org.example;

import jakarta.persistence.*;
import org.example.entities.*;
import org.example.repositories.CustomerRepo;
import org.example.repositories.RestaurantRepo;
import org.hibernate.jpa.HibernatePersistenceConfiguration;

import java.time.LocalDateTime;
import java.util.List;

public class App {
    public static void main(String[] args) {


        final PersistenceConfiguration cfg = new HibernatePersistenceConfiguration("enf")
            .jdbcUrl("jdbc:mysql://localhost:3306/app_db")
            .jdbcUsername("grupp2")
            .jdbcPassword(System.getenv("PASSWORD"))
            .property("hibernate.hbm2ddl.auto", "update")
            .property("hibernate.show_sql", "true")
            .property("hibernate.format_sql", "true")
            .property("hibernate.highlight_sql", "true")
            .managedClasses(Booking.class, Customer.class, DiningTable.class, OpeningHours.class, Restaurant.class);

        try (EntityManagerFactory emf = cfg.createEntityManagerFactory()) {
            emf.runInTransaction(em -> {

                LocalDateTime now = LocalDateTime.now();
                LocalDateTime nowPlusTwoHours = LocalDateTime.now().plusHours(2);

                DataLoader loader = new DataLoader();
                loader.runDataLoader(em);


                final RestaurantRepo restaurantRepo = new RestaurantRepo(em);
                final CustomerRepo customerRepo = new CustomerRepo(em);
                final BookingFactory bookingFactory = new BookingFactory();
                Restaurant restaurants = restaurantRepo.fetchRestaurantByName("TOSO");

                Customer customer = customerRepo.fetchCustomerByFirstNameAndLastName("Test", "Testsson");

                Booking b = bookingFactory.createBooking(restaurants, customer, 1l, now, nowPlusTwoHours);
                em.persist(b);
                em.flush();
                System.out.println("BOOKING with ID CREATED: " + b.getId());


                //Todo: create entities
            });
        }


    }
    }
