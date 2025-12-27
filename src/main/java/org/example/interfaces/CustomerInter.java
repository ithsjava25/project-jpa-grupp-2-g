package org.example.repositories;

import org.example.entities.Customer;
import java.util.List;

public interface CustomerInter {

    void save(Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();

    void update(Customer customer);

    void delete(Customer customer);
}