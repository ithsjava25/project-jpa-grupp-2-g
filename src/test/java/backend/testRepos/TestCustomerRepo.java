package backend.testRepos;

import backend.config.TestConnectionProvider;
import backend.repositories.CustomerRepo;
import jakarta.persistence.EntityManagerFactory;

public class TestCustomerRepo extends CustomerRepo {

    @Override
    public EntityManagerFactory getEMF() {
        return TestConnectionProvider.getEMF();
    }
}
