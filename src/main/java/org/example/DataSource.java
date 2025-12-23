package org.example;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceConfiguration;
import org.example.entities.*;
import org.hibernate.jpa.HibernatePersistenceConfiguration;

/**
 * This class handles the connection to the database through JPA, Hibernate and HikariCP
 * It creates one centralized EntityManagerFactory that can be used in the whole program
 */
public class DataSource {

    private static final PersistenceConfiguration cfg = new HibernatePersistenceConfiguration("persist")
        .jdbcUrl("jdbc:mysql://localhost:3306/app_db")
        .jdbcUsername("grupp2")
        .jdbcPassword(System.getenv("PASSWORD"))
        .property("hibernate.connection.provider_class", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider")
        .property("hibernate.hbm2ddl.auto", "update")
        .property("hibernate.show_sql", "true")
        .property("hibernate.format_sql", "true")
        .property("hibernate.highlight_sql", "true")
        .managedClasses(Booking.class, Customer.class, DiningTable.class, OpeningHours.class, Restaurant.class);

    private static final EntityManagerFactory EMF = cfg.createEntityManagerFactory();

    private DataSource(){}

    public static EntityManagerFactory getEMF() {
        return EMF;
    }

}
