package org.example;

import jakarta.persistence.*;
import org.example.entities.*;
import org.hibernate.jpa.HibernatePersistenceConfiguration;

public class App {
    public static void main(String[] args) {

        ConnectionProvider.initialize();
    }
}
