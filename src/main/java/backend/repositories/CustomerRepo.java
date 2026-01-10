package backend.repositories;

import backend.entities.Customer;

public class CustomerRepo extends BaseRepo<Customer> {
    public CustomerRepo(){
        super(Customer.class);
    }

    public Customer fetchCustomerByFirstNameLastLastNameAndPhoneNumber(String firstName, String lastName,String phoneNumber){
        return callInTransaction(em ->
            em.createQuery("""
                    SELECT c FROM Customer c
        WHERE c.firstName = :firstName
        AND c.lastName = :lastName
        AND c.phoneNumber = :phoneNumber
       """, Customer.class)
            .setParameter("firstName", firstName)
            .setParameter("lastName", lastName)
                .setParameter("phoneNumber", phoneNumber)
            .getSingleResultOrNull());

    }

    public Customer fetchCustomerByEmail(String email) {
        return callInTransaction(em ->
            em.createQuery("""
                    SELECT c FROM Customer c
        WHERE c.email = :email
        """, Customer.class)
                .setParameter("email", email)
                .getSingleResultOrNull());

    }
}
