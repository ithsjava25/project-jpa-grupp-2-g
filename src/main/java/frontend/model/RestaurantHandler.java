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
     * Retrieves a list of restaurants based on a search string or category.
     * <p>
     * If the input is "All" or empty, it returns all restaurants.
     * Otherwise, it first attempts to find matches by category,
     * falling back to a name-based search if no category match is found.
     * </p>
     * * @param input the search term or category name.
     * @return a {@link List} of matching {@link Restaurant} objects.
     */
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
