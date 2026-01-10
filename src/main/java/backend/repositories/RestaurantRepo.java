package backend.repositories;

import backend.ConnectionProvider;
import backend.entities.Restaurant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class RestaurantRepo extends BaseRepo<Restaurant>{
    private final EntityManagerFactory emf = ConnectionProvider.getEMF();

    public RestaurantRepo(){
        super(Restaurant.class);
    }

    public List<Restaurant> findRestaurantsMatchingSearch(String name){
        try(EntityManager em = emf.createEntityManager()) {
            String query = "Select e from " + entityClass.getSimpleName() + " e where upper(e.name) like upper(?1) ";
            return em.createQuery(query, entityClass).setParameter(1, "%" + name + "%").getResultList();
        }
    }


    public Restaurant fetchRestaurantByName(String name) {
        return callInTransaction(em ->
            em.createQuery("""
                SELECT r FROM Restaurant r
                WHERE r.name = :restaurant
            """, Restaurant.class)
                .setParameter("restaurant", name)
                .getSingleResultOrNull());

    }

}
