package org.example.factories;

import org.example.entities.DiningTable;
import org.example.entities.Restaurant;

public class DiningTableFactory {

    public static DiningTable createTable(int tableNumber, int seats, Restaurant restaurant) {
        DiningTable table = new DiningTable();
        table.setTableNumber(tableNumber);
        table.setSeats(seats);
        table.setRestaurant(restaurant);
        return table;
    }
}
