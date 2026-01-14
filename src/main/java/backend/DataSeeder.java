package backend;

import backend.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.List;

public class DataSeeder {

    public static void populateDatabase() {
        EntityManager em = ConnectionProvider.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            List<Restaurant> restaurants = List.of(
                new Restaurant("Pizzeria Napoli", "Pizza", "Storgatan 12", new BigDecimal("120.0"), 4.5, "pizzeriaNapoli.png"),
                new Restaurant("Sushi Yama", "Sushi", "Kungsgatan 5", new BigDecimal("180.0"), 4.2, "sushiYama.png"),
                new Restaurant("Burger House", "Hamburger", "Sveavägen 44", new BigDecimal("140.0"), 4.0, "burgerHouse.png"),
                new Restaurant("Green Garden", "Vegetarian", "Odengatan 21", new BigDecimal("160.0"), 4.6, "greenGarden.png"),
                new Restaurant("Curry Palace", "Indian", "Hornsgatan 88", new BigDecimal("150.0"), 4.3, "curryPalace.png"),
                new Restaurant("TOSO", "Asian", "Götaplatsen 1", new BigDecimal("400.0"), 4.9, "toso.png"),
                new Restaurant("Lilla Taverna", "Greek", "Olivedalsgatan 17", new BigDecimal("180.0"), 3.7, "lillaTaverna.png")
            );

            for (Restaurant r : restaurants) {
                Long count = em.createQuery("SELECT COUNT(r) FROM Restaurant r WHERE r.name = :name", Long.class)
                    .setParameter("name", r.getName())
                    .getSingleResult();

                if (count == 0) {
                    em.persist(r);
                    seedTablesForRestaurant(em, r);
                }
            }

            List<Customer> customers = List.of(
                new Customer("Anna", "Svensson", "0701111111", "anna@mail.se"),
                new Customer("Erik", "Larsson", "0702222222", "erik@mail.se"),
                new Customer("Sara", "Nilsson", "0703333333", "sara@mail.se"),
                new Customer("Johan", "Karlsson", "0704444444", "johan@mail.se"),
                new Customer("Emma", "Andersson", "0705555555", "emma@mail.se")
            );

            for (Customer c : customers) {
                Long count = em.createQuery("SELECT COUNT(c) FROM Customer c WHERE c.phoneNumber = :phone", Long.class)
                    .setParameter("phone", c.getPhoneNumber())
                    .getSingleResult();

                if (count == 0) {
                    em.persist(c);
                }
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void seedTablesForRestaurant(EntityManager em, Restaurant restaurant) {
        int[] capacities = {2, 4, 6, 6, 8, 8};

        for (int i = 0; i < capacities.length; i++) {
            DiningTable table = new DiningTable();
            table.setTableNumber(i + 1);
            table.setSeatCapacity(capacities[i]);
            table.setRestaurant(restaurant);
            em.persist(table);
        }
    }

}
