package backend.testRepos;

import backend.config.TestConnectionProvider;
import backend.repositories.DiningTableRepo;
import jakarta.persistence.EntityManagerFactory;

public class TestDiningTableRepo extends DiningTableRepo {
    @Override
    public EntityManagerFactory getEMF() {
        return TestConnectionProvider.getEMF();
    }
}
