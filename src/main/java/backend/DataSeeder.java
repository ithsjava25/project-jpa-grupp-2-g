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

            Restaurant napoli = new Restaurant("Pizzeria Napoli", "Pizza", "Storgatan 12", new BigDecimal("120.0"), 4.5, "pizzeriaNapoli.png");
            Restaurant yama = new Restaurant("Sushi Yama", "Sushi", "Kungsgatan 5", new BigDecimal("180.0"), 4.2, "sushiYama.png");
            Restaurant burger = new Restaurant("Burger House", "Hamburger", "Sveavägen 44", new BigDecimal("140.0"), 4.0, "burgerHouse.png");
            Restaurant green = new Restaurant("Green Garden", "Vegetarian", "Odengatan 21", new BigDecimal("160.0"), 4.6, "greenGarden.png");
            Restaurant curry = new Restaurant("Curry Palace", "Indian", "Hornsgatan 88", new BigDecimal("150.0"), 4.3, "curryPalace.png");
            Restaurant toso = new Restaurant("TOSO", "Asian", "Götaplatsen 1", new BigDecimal("400.0"), 4.9, "toso.png");
            Restaurant taverna = new Restaurant("Lilla Taverna", "Greek", "Olivedalsgatan 17", new BigDecimal("180.0"), 3.7, "lillaTaverna.png");

            List<Restaurant> restaurants = List.of(napoli, yama, burger, green, curry, toso, taverna);

            for (Restaurant r : restaurants) {
                em.persist(r);
                seedTablesForRestaurant(em, r);
            }

            em.persist(new Customer("Anna", "Svensson", "0701111111", "anna@mail.se"));
            em.persist(new Customer("Erik", "Larsson", "0702222222", "erik@mail.se"));
            em.persist(new Customer("Sara", "Nilsson", "0703333333", "sara@mail.se"));
            em.persist(new Customer("Johan", "Karlsson", "0704444444", "johan@mail.se"));
            em.persist(new Customer("Emma", "Andersson", "0705555555", "emma@mail.se"));

            tx.commit();
            System.out.println("Database seeded successfully!");
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
