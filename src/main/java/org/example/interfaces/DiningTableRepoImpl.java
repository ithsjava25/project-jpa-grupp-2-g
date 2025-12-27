import jakarta.persistence.EntityManager;
import org.example.entities.DiningTable;
import org.example.repositories.DiningTableInter;

import java.util.List;

public class DiningTableRepoImpl implements DiningTableInter {

    private final EntityManager em;

    public DiningTableRepoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(DiningTable table) {
        em.getTransaction().begin();
        em.persist(table);
        em.getTransaction().commit();
    }

    @Override
    public DiningTable findById(Long id) {
        return em.find(DiningTable.class, id);
    }

    @Override
    public List<DiningTable> findAll() {
        return em.createQuery("SELECT t FROM DiningTable t", DiningTable.class)
                .getResultList();
    }

    @Override
    public void update(DiningTable table) {
        em.getTransaction().begin();
        em.merge(table);
        em.getTransaction().commit();
    }

    @Override
    public void delete(DiningTable table) {
        em.getTransaction().begin();
        em.remove(em.contains(table) ? table : em.merge(table));
        em.getTransaction().commit();
    }
}
