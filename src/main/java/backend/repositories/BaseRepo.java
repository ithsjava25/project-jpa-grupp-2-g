package backend.repositories;

import jakarta.persistence.*;
import backend.ConnectionProvider;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
/**
 * Base repository class that provides common database operations.
 *
 * Handles basic CRUD operations and transaction management
 * for any JPA entity.
 */
public abstract class BaseRepo<T> {

    public Class<T> entityClass;
    private final EntityManagerFactory emf = ConnectionProvider.getEMF();

    public BaseRepo(){}

    public BaseRepo(Class <T> entityClass){
        this.entityClass = entityClass;
    }

    public void executeInTransaction(Consumer<EntityManager> action){
        try(EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try{
                et.begin();
                action.accept(em);
                et.commit();
            } catch (Exception e) {
                if(et.isActive())
                    et.rollback();
                throw new RuntimeException(e);
            }
        }
    }

    public <R> R callInTransaction(Function<EntityManager, R> action) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                R result = action.apply(em);
                tx.commit();
                return result;
            } catch (RuntimeException e) {
                if (tx.isActive()) tx.rollback();
                throw e;
            }
        }
    }

    public void add(T object){
        executeInTransaction(em -> em.persist(object));
    }

    public void addALL(List<T> objects){
        executeInTransaction(em -> {
            for(T object : objects)
                em.persist(object);
        });
    }

    public void update(T object){
        executeInTransaction(em -> em.merge(object));
    }

    public void delete(T object){
        executeInTransaction(em -> em.remove(em.merge(object)));
    }

    public void deleteById(Long id){
        executeInTransaction(em -> {
            T entity = em.find(entityClass, id);
            if(entity != null) {
                em.remove(entity);
            }
        });
    }

    public void deleteAll() {
        executeInTransaction(em -> {
            String query = "DELETE FROM " + entityClass.getSimpleName();
            em.createQuery(query).executeUpdate();
        });
    }

    public List<T> findAll(){
        try(EntityManager em = emf.createEntityManager()) {
            String query = "Select e from " + entityClass.getName() + " e";
            return em.createQuery(query, entityClass).getResultList();
        }
    }

    public T findById(Long id){
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(entityClass, id);
        }
    }

}
