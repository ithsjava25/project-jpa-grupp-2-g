package org.example.repositories;

import jakarta.persistence.*;
import org.example.ConnectionProvider;

import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractJpaRepo <T, ID> {

    private final Class<T> entityClass;
    private final EntityManagerFactory emf = ConnectionProvider.getEMF();

    public AbstractJpaRepo(Class <T> entityClass){
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

    public boolean deleteById(ID id){
        final boolean[] deleted = {false};
        executeInTransaction(em -> {
            T entity = em.find(entityClass, id);
            if(entity != null) {
                em.remove(entity);
                deleted[0] = true;
            }
        });
        return deleted[0];
    }

    public List<T> findAll(){
        try(EntityManager em = emf.createEntityManager()) {
            String query = "Select * from " + entityClass.getName();
            return em.createQuery(query, entityClass).getResultList();
        }
    }

    public T findById(ID id){
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(entityClass, id);
        }
    }

    public List<T> findByProperty(String column, Object value) {
        try(EntityManager em = emf.createEntityManager()) {
            String query = "Select * from " + entityClass.getSimpleName() + " where ? like ?";
            return em.createQuery(query, entityClass).setParameter(1, column).setParameter(2, value).getResultList();
        }
    }

}
