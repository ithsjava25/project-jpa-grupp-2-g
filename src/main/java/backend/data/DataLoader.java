package backend.data;

import jakarta.persistence.EntityManager;
import backend.entities.Customer;
import backend.entities.DiningTable;
import backend.entities.Restaurant;

import java.math.BigDecimal;

public class DataLoader {

    //Loads the preset data into the database.
    public void runDataLoader(EntityManager em) {
        //Resets the database.
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE Booking").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE Restaurant").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE Customer").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE DiningTable").executeUpdate();

        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
        em.flush();

        //Adds the test restaurant-
        Restaurant restaurant = new Restaurant();
        restaurant.setName("TOSO");
        restaurant.setAddress("Götaplatsen 1");
        restaurant.setCategory("Asian");
        restaurant.setRating(0.0);
        restaurant.setMeanPrice(BigDecimal.valueOf(1000));
        restaurant.setImagePath("toso.png");
        em.persist(restaurant);

        //Adds another restaurant -
        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Lilla Taverna");
        restaurant2.setAddress("Olivedalsgatan 17");
        restaurant2.setCategory("Greek");
        restaurant2.setRating(0.0);
        restaurant2.setMeanPrice(BigDecimal.valueOf(500));
        restaurant2.setImagePath("lillaTaverna.png");
        em.persist(restaurant2);


        //Adds a customer
        Customer customer = new Customer("Test", "Testsson", "12345", "test@email.com");
        em.persist(customer);

        //Adds a dining table
        DiningTable diningTable = new DiningTable(1, 8, restaurant);
        em.persist(diningTable);

        //Adds a second dining table
        diningTable = new DiningTable(2, 8, restaurant);
        em.persist(diningTable);
        em.flush();
    }
}
