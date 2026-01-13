package backend.repositories;

import jakarta.persistence.*;
import backend.ConnectionProvider;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class BaseRepo<T> {

    public Class<T> entityClass;


    public BaseRepo(Class <T> entityClass){
        this.entityClass = entityClass;
    }

    public EntityManagerFactory getEMF(){
        return ConnectionProvider.getEMF();
    }


    public void executeInTransaction(Consumer<EntityManager> action){
        try(EntityManager em = getEMF().createEntityManager()) {
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
        try (EntityManager em = getEMF().createEntityManager()) {
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

    public List<T> findAll(){
        try(EntityManager em = getEMF().createEntityManager()) {
            String query = "Select e from " + entityClass.getName() + " e";
            return em.createQuery(query, entityClass).getResultList();
        }
    }

    public T findById(Long id){
        try(EntityManager em = getEMF().createEntityManager()) {
            return em.find(entityClass, id);
        }
    }

    public List<T> findByProperty(String column, Object value) {
        try(EntityManager em = getEMF().createEntityManager()) {
            String query = "Select e from " + entityClass.getSimpleName() + " e where ?1 like ?2";
            return em.createQuery(query, entityClass).setParameter(1, column).setParameter(2, value).getResultList();
        }
    }

}
