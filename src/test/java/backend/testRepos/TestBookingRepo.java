package backend.testRepos;

import backend.config.TestConnectionProvider;
import backend.repositories.BookingRepo;
import jakarta.persistence.EntityManagerFactory;

public class TestBookingRepo extends BookingRepo {

    @Override
    public EntityManagerFactory getEMF() {
        return TestConnectionProvider.getEMF();
    }
}
