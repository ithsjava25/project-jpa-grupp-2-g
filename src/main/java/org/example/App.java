package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceConfiguration;
import org.example.entities.*;
import org.hibernate.jpa.HibernatePersistenceConfiguration;

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

        try(EntityManagerFactory emf = cfg.createEntityManagerFactory()){
            emf.runInTransaction( em->{
                DiningTable table = new DiningTable();
                em.persist(table);
            });
        }


    }
}
