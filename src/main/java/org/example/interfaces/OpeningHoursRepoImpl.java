import jakarta.persistence.EntityManager;
import org.example.entities.OpeningHours;
import org.example.repositories.OpeningHoursInter;

import java.util.List;

public class OpeningHoursRepoImpl implements OpeningHoursInter {

    private final EntityManager em;

    public OpeningHoursRepoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(OpeningHours openingHours) {
        em.getTransaction().begin();
        em.persist(openingHours);
        em.getTransaction().commit();
    }

    @Override
    public OpeningHours findById(Long id) {
        return em.find(OpeningHours.class, id);
    }

    @Override
    public List<OpeningHours> findAll() {
        return em.createQuery("SELECT o FROM OpeningHours o", OpeningHours.class)
                .getResultList();
    }

    @Override
    public void update(OpeningHours openingHours) {
        em.getTransaction().begin();
        em.merge(openingHours);
        em.getTransaction().commit();
    }

    @Override
    public void delete(OpeningHours openingHours) {
        em.getTransaction().begin();
        em.remove(em.contains(openingHours) ? openingHours : em.merge(openingHours));
        em.getTransaction().commit();
    }
}