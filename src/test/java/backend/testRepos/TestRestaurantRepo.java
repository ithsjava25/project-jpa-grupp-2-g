package backend.testRepos;

import backend.config.TestConnectionProvider;
import backend.repositories.RestaurantRepo;
import jakarta.persistence.EntityManagerFactory;

public class TestRestaurantRepo extends RestaurantRepo {
    @Override
    public EntityManagerFactory getEMF() {
        return TestConnectionProvider.getEMF();
    }
}
