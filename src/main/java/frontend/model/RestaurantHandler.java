package frontend.model;

import backend.entities.Restaurant;
import backend.repositories.RestaurantRepo;

import java.util.List;

/**
 * Helper class for handling restaurant selection and search.
 *
 * Stores the currently selected restaurant and provides
 * search functionality.
 */
public class RestaurantHandler {

    private static Restaurant currentRestaurant;

    public static Restaurant getCurrentRestaurant() {
        return currentRestaurant;
    }

    public static void setCurrentRestaurant(Restaurant restaurant) {
        currentRestaurant = restaurant;
    }


    /**
     * Searches for restaurants matching the given name.
     *
     * If no restaurants are found, all restaurants are returned.
     *
     */
    public static List<Restaurant> getResturantList(String name){
        RestaurantRepo repo = new RestaurantRepo();
        List<Restaurant> result = repo.findRestaurantsMatchingSearch(name);

        if(result.isEmpty())
            return repo.findRestaurantsMatchingSearch("");
        else
            return result;
    }
}
