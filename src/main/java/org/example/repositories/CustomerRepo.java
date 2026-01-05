package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.entities.Booking;
import org.example.entities.Customer;
import org.example.entities.Restaurant;

import java.util.List;

public class CustomerRepo {

    EntityManager em;

    public CustomerRepo(EntityManager em) {
        this.em = em;
    }
    public Customer fetchCustomerByFirstNameAndLastName(String firstName, String lastName) {

        return em.createQuery("""
        SELECT c FROM Customer c
        WHERE c.firstName = :firstName
        AND  c.lastName = :lastName
        """, Customer.class)
            .setParameter("firstName", firstName)
            .setParameter("lastName", lastName)
            .getSingleResultOrNull();
    }
}
