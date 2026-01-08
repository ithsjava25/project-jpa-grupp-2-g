package backend;

import backend.entities.Customer;
import backend.repositories.CustomerRepo;
import io.github.cdimascio.dotenv.Dotenv;

public class App {
    public static void main(String[] args) {
        ConnectionProvider.initialize();
    }
}
