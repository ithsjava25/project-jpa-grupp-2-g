package backend;

import backend.entities.*;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceConfiguration;
import backend.entities.*;
import org.hibernate.jpa.HibernatePersistenceConfiguration;

/**
 * This class handles the connection to the database through JPA, Hibernate and HikariCP
 * It creates one centralized EntityManagerFactory that can be used in the whole program
 */
public class ConnectionProvider {
    private static final Dotenv env = Dotenv.configure()
        .ignoreIfMissing()
        .load();
    private static final PersistenceConfiguration cfg;
    private static final EntityManagerFactory EMF;

    static{
        try{
            if(env.get("TEST").equals("TRUE")){

                cfg = new HibernatePersistenceConfiguration("persistence")
                    .jdbcUrl("jdbc:mysql://localhost:3307/testdb")
                    .jdbcUsername("user")
                    .jdbcPassword("password")
                    .property("hibernate.connection.provider_class", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider")
                    .property("hibernate.hbm2ddl.auto", "update")
                    .property("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .property("hibernate.show_sql", "true")
                    .property("hibernate.format_sql", "true")
                    .property("hibernate.highlight_sql", "true")
                    .managedClasses(Booking.class, Customer.class, DiningTable.class, OpeningHours.class, Restaurant.class);
            }
            else{
                cfg = new HibernatePersistenceConfiguration("persistence")
                    .jdbcUrl("jdbc:mysql://localhost:3306/app_db?createDatabaseIfNotExist=true")
                    .jdbcUsername("grupp2")
                    .jdbcPassword(env.get("PASSWORD"))
                    .property("hibernate.connection.provider_class", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider")
                    .property("hibernate.hbm2ddl.auto", "update")
                    .property("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .property("hibernate.show_sql", "true")
                    .property("hibernate.format_sql", "true")
                    .property("hibernate.highlight_sql", "true")
                    .managedClasses(Booking.class, Customer.class, DiningTable.class, OpeningHours.class, Restaurant.class);

            }


                EMF = cfg.createEntityManagerFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionProvider(){}

    public static void initialize(){}

    public static EntityManagerFactory getEMF() {
        return EMF;
    }


}

