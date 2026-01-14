package backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "table_id", nullable = false)
    private Long tableId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "booking_start", nullable = false)
    private LocalTime bookingStart;

    @Column(name = "booking_end", nullable = false)
    private LocalTime bookingEnd;


    public Booking() {
    }

    public Booking(Restaurant restaurant,
                   Customer customer,
                   Long tableId,
                   LocalTime bookingStart,
                   LocalTime bookingEnd,
                   LocalDate date) {
        this.restaurant = restaurant;
        this.customer = customer;
        this.tableId = tableId;
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;
        this.date = date;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getTableId() {
        return tableId;
    }

    public LocalTime getBookingStart() {
        return bookingStart;
    }

    public LocalTime getBookingEnd() {
        return bookingEnd;
    }

    public Customer getCustomer() {
        return customer;
    }


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    // 🔹 Setters (NYTT)

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public void setBookingStart(LocalTime bookingStart) {
        this.bookingStart = bookingStart;
    }

    public void setBookingEnd(LocalTime bookingEnd) {
        this.bookingEnd = bookingEnd;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public boolean equals(Booking booking) {
        if(this.id.equals( booking.id)
        && this.tableId.equals(booking.tableId)
        && this.bookingStart.equals(booking.bookingStart)
        && this.bookingEnd.equals(
            booking.bookingEnd)
            ){
            return true;
        }



        return false;

    }
}
