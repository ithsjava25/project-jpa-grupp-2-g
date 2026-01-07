package frontend;

import backend.entities.Restaurant;
import backend.repositories.RestaurantRepo;

import java.util.List;

public class HelloModel {
    private final static String appName = "Grupp2";

    public List<Restaurant> getResturantList(String name){
        RestaurantRepo repo = new RestaurantRepo();
        List<Restaurant> result = repo.findRestaurantsMatchingSearch(name);

        if(result.isEmpty())
            return repo.findRestaurantsMatchingSearch("");
        else
            return result;
    }

    public String getAppName(){
        return appName;
    }
}
