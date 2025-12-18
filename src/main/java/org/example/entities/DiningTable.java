package org.example.entities;

import jakarta.persistence.*;

@Entity
public class DiningTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_number", nullable = false)
    private int tableNumber;

    @Column(name = "capacity", nullable = false)
    private int seatCapacity;



    public DiningTable() {

    }

    public DiningTable(int tableNumber, int seatCapacity) {
        this.tableNumber = tableNumber;
        this.seatCapacity = seatCapacity;
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


    @Override
    public String toString() {
        return "DiningTable{" +
            "id=" + id +
            ", tableNumber=" + tableNumber +
            ", seatCapacity=" + seatCapacity +
            '}';
    }
}
