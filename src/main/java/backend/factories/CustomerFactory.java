package backend.factories;

import backend.entities.Customer;
import backend.repositories.CustomerRepo;
import backend.services.CustomerService;

public class CustomerFactory {

    public Customer createCustomer(
        String firstName,
        String lastName,
        String email,
        String phoneNumber) {

        if (!validateName(firstName))
            throw new IllegalArgumentException("Invalid first name");

        if (!validateName(lastName))
            throw new IllegalArgumentException("Invalid last name");

        if (!validatePhoneNumber(phoneNumber))
            throw new IllegalArgumentException("Invalid phone number");

        if (!validateEmail(email))
            throw new IllegalArgumentException("Invalid email");

        Customer customer = new Customer(firstName, lastName, email, phoneNumber);

        return customer;
    }

    private boolean validateName(String name) {
        return name != null
            && !name.isBlank()
            && name.matches("^[a-zA-ZåäöÅÄÖ]+$");
    }

    private boolean validatePhoneNumber(String number) {
        return number != null;
    }

    private boolean validateEmail(String email) {
        return email != null;
    }
}
