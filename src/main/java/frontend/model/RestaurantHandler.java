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

    public static List<Restaurant> getResturantList(String input){
        RestaurantRepo repo = new RestaurantRepo();

        if(input.equalsIgnoreCase("All") || input.isEmpty()) {
            return repo.findRestaurantsMatchingSearch("");
        }

        List<Restaurant> byCategory = repo.fetchRestaurantsByCategory(input);
        if(!byCategory.isEmpty()) {
            return byCategory;
        }

        return repo.findRestaurantsMatchingSearch(input);
    }
}
