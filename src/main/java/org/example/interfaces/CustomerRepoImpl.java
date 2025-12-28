import java.util.ArrayList;
import java.util.List;

public class CustomerRepoImpl implements CrudRepository<Customer, Integer> {

    private List<Customer> customers = new ArrayList<>();

    @Override
    public void add(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Customer getById(Integer id) {
        for (Customer c : customers) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return customers;
    }

    @Override
    public void update(Customer customer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customer.getId()) {
                customers.set(i, customer);
                return;
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        customers.removeIf(c -> c.getId() == id);
    }
}