package se.restaurantbooking.entity;

import jakarta.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String phoneNumber, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank() || firstName.length() > 50) {
            throw new IllegalArgumentException("First name is required and max 50 characters");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank() || lastName.length() > 50) {
            throw new IllegalArgumentException("Last name is required and max 50 characters");
        }
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("[0-9]+")) {
            throw new IllegalArgumentException("Phone number must contain only numbers");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}