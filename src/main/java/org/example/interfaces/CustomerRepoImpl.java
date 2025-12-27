import jakarta.persistence.EntityManager;
import org.example.entities.Customer;
import org.example.repositories.CustomerInter;

import java.util.List;

public class CustomerRepoImpl implements CustomerInter {

    private final EntityManager em;

    public CustomerRepoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Customer customer) {
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
    }

    @Override
    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class)
                .getResultList();
    }

    @Override
    public void update(Customer customer) {
        em.getTransaction().begin();
        em.merge(customer);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Customer customer) {
        em.getTransaction().begin();
        em.remove(em.contains(customer) ? customer : em.merge(customer));
        em.getTransaction().commit();
    }
}
