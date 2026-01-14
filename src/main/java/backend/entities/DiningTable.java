package backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "DiningTable", uniqueConstraints = {
    @UniqueConstraint(name = "uq_diningtable", columnNames = {"restaurant_id", "table_number"})
})
public class DiningTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "table_number", nullable = false)
    private int tableNumber;

    @Column(name = "capacity", nullable = false)
    private int seatCapacity;

    public DiningTable() {

    }

    public DiningTable(int tableNumber, int seatCapacity, Restaurant restaurant) {
        this.tableNumber = tableNumber;
        this.seatCapacity = seatCapacity;
        this.restaurant = restaurant;
    }



    public Long getId() {
        return id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "DiningTable{" +
            "id=" + id +
            ", tableNumber=" + tableNumber +
            ", seatCapacity=" + seatCapacity +
            ", restaurant=" + restaurant.getName() +
            '}';
    }
}
