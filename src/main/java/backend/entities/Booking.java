package backend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
        name = "booking_table",
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "table_id")
    )
    private Set<DiningTable> tables = new HashSet<DiningTable>();

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "table_id", nullable = false)
    private Long tableId;

    @Column(name = "booking_start", nullable = false)
    private LocalDateTime bookingStart;

    @Column(name = "booking_end", nullable = false)
    private LocalDateTime bookingEnd;


    public Booking() {
    }

    public Booking(Restaurant restaurant,
                   Customer customer,
                   Long tableId,
                   LocalDateTime bookingStart,
                   LocalDateTime bookingEnd) {
        this.restaurant = restaurant;
        this.customer = customer;
        this.tableId = tableId;
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getTableId() {
        return tableId;
    }

    public LocalDateTime getBookingStart() {
        return bookingStart;
    }

    public LocalDateTime getBookingEnd() {
        return bookingEnd;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<DiningTable> getTables() {
        return tables;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    // 🔹 Setters (NYTT)

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setTables(Set<DiningTable> tables) {
        this.tables = tables;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public void setBookingStart(LocalDateTime bookingStart) {
        this.bookingStart = bookingStart;
    }

    public void setBookingEnd(LocalDateTime bookingEnd) {
        this.bookingEnd = bookingEnd;
    }

    @Override
    public String toString() {
        return "Booking{" +
            "id=" + id +
            "custumor=" + customer.getFirstName() + " " + customer.getLastName() +
            ", restaurantId=" + restaurant +
            ", tableId=" + tableId +
            ", bookingStart=" + bookingStart +
            ", bookingEnd=" + bookingEnd +
            '}';
    }
}
