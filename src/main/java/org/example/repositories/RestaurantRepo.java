package org.example.repositories;

import org.example.entities.Restaurant;

public class RestaurantRepo extends BaseRepo<Restaurant> {
    public RestaurantRepo(){
        super(Restaurant.class);
    }

}
