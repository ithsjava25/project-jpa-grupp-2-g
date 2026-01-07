package backend.repositories;

import backend.entities.Restaurant;

public class RestaurantRepo extends BaseRepo<Restaurant>{

    public RestaurantRepo(){
        super(Restaurant.class);
    }

}
