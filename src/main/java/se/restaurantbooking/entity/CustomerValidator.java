package se.restaurantbooking.validation;

import se.restaurantbooking.entity.Customer;

public class CustomerValidator {

    public static void validate(Customer customer) {
        if (customer.getFirstName() == null || customer.getFirstName().isBlank() || customer.getFirstName().length() > 50) {
            throw new IllegalArgumentException("First name is required and max 50 characters");
        }

        if (customer.getLastName() == null || customer.getLastName().isBlank() || customer.getLastName().length() > 50) {
            throw new IllegalArgumentException("Last name is required and max 50 characters");
        }

        if (customer.getPhoneNumber() == null || !customer.getPhoneNumber().matches("[0-9]+")) {
            throw new IllegalArgumentException("Phone number must contain only numbers");
        }

        if (customer.getEmail() == null || !customer.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }
}
