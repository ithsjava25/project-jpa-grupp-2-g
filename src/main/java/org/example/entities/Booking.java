package org.example.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "table_id", nullable = false)
    private Long tableId;

    @Column(name = "booking_start", nullable = false)
    private LocalDateTime bookingStart;

    @Column(name = "booking_end", nullable = false)
    private LocalDateTime bookingEnd;

    // Krävs av JPA
    protected Booking() {
    }

    public Booking(Long restaurantId,
                   Long customerId,
                   Long tableId,
                   LocalDateTime bookingStart,
                   LocalDateTime bookingEnd) {
        this.restaurantId = restaurantId;
        this.customerId = customerId;
        this.tableId = tableId;
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public Long getCustomerId() {
        return customerId;
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

    // 🔹 Setters (NYTT)
    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    // 🔹 toString (NYTT)
    @Override
    public String toString() {
        return "Booking{" +
            "id=" + id +
            ", restaurantId=" + restaurantId +
            ", customerId=" + customerId +
            ", tableId=" + tableId +
            ", bookingStart=" + bookingStart +
            ", bookingEnd=" + bookingEnd +
            '}';
    }
}
