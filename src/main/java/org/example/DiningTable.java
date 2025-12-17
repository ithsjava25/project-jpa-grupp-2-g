package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "Dining tables")
public class DiningTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tableNumber;

    private int seatCapacity;

    public Long getId() {
        return id;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }


    @Override
    public String toString() {
        return "DiningTable{" +
            "id=" + id +
            ", tableNumber='" + tableNumber + '\'' +
            ", seatCapacity=" + seatCapacity +
            '}';
    }
}
