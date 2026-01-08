package backend;

import backend.data.DataLoader;
import backend.entities.Customer;
import backend.repositories.CustomerRepo;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class App {
    public static void main(String[] args) {
        ConnectionProvider.initialize();

        try(EntityManager em = ConnectionProvider.getEMF().createEntityManager()){
            EntityTransaction et = em.getTransaction();
            try{
                et.begin();
                DataLoader data = new DataLoader();
                data.runDataLoader(em);
                et.commit();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
