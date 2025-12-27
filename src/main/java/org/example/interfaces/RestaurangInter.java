package org.example.factories;

import org.example.entities.Restaurant;
import java.util.ArrayList;

public class RestaurantFactory {

    public static Restaurant createRestaurant(String name, String genre, double rating) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setGenre(genre);
        restaurant.setRating(rating);
        restaurant.setTables(new ArrayList<>()); // tom lista för bord
        return restaurant;
    }
}
