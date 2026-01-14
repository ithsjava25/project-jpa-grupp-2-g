package frontend.model;

import backend.entities.Restaurant;
import backend.repositories.RestaurantRepo;

import java.util.List;

public class RestaurantHandler {

    private static Restaurant currentRestaurant;

    public static Restaurant getCurrentRestaurant() {
        return currentRestaurant;
    }

    public static void setCurrentRestaurant(Restaurant restaurant) {
        currentRestaurant = restaurant;
    }

    public static List<Restaurant> getResturantList(String name){
        RestaurantRepo repo = new RestaurantRepo();
        List<Restaurant> result = repo.findRestaurantsMatchingSearch(name);

        if(result.isEmpty())
            return repo.findRestaurantsMatchingSearch("");
        else
            return result;
    }
}
