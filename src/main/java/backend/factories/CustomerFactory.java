package backend.factories;

import backend.entities.Customer;
import backend.repositories.CustomerRepo;
import backend.services.CustomerService;

public class CustomerFactory {

    private final CustomerRepo repo = new CustomerRepo();
    private final CustomerService service = new CustomerService();

    public void createCustomer(String name, String lastName, String phoneNumber, String email){
        if(service.validateName(name) && service.validateName(lastName) &&
            service.validatePhoneNumber(phoneNumber) && service.validateEmail(email)){
            Customer customer = new Customer(name, lastName, phoneNumber, email);
            repo.add(customer);
        }
    }
}
