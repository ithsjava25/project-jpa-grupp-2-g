package backend.config;

import backend.entities.*;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceConfiguration;
import org.hibernate.jpa.HibernatePersistenceConfiguration;

/**
 * This class handles the connection to the database through JPA, Hibernate and HikariCP
 * It creates one centralized EntityManagerFactory that can be used in the whole program
 */
public class TestConnectionProvider {


    private static final EntityManagerFactory EMF =
        new HibernatePersistenceConfiguration("test")
            .jdbcUrl("jdbc:h2:mem:test;")
            .jdbcUsername("sa")
            .jdbcPassword("")
            .property("hibernate.hbm2ddl.auto", "create-drop")
            .property("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
            .property("hibernate.show_sql", "false")
            .managedClasses(
                Booking.class,
                Customer.class,
                DiningTable.class,
                OpeningHours.class,
                Restaurant.class
            )
            .createEntityManagerFactory();

    public static EntityManagerFactory getEMF() {
        return EMF;
    }
}
