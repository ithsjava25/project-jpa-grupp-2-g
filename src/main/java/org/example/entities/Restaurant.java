package org.example.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String address;
    private BigDecimal priceRange;
    private double rating;
    private LocalTime openingTime;
    private LocalTime closingTime;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(BigDecimal price_range) {
        this.priceRange = price_range;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime opening_time) {
        this.openingTime = opening_time;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closing_time) {
        this.closingTime = closing_time;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", category='" + category + '\'' +
            ", address='" + address + '\'' +
            ", priceRange=" + priceRange +
            ", rating=" + rating +
            ", openingTime=" + openingTime +
            ", closingTime=" + closingTime +
            '}';
    }
}
