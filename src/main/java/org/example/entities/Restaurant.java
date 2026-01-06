package org.example.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "openingHoursId")
    private List<OpeningHours> openingHours = new ArrayList<>();

    @Column(name = "mean_range")
    private BigDecimal meanPrice;

    private String name;
    private String category;
    private String address;
    private double rating;

    public Restaurant(String name, String category, String address, BigDecimal meanPrice, double rating) {
        this.name = name;
        this.category = category;
        this.address = address;
        this.meanPrice = meanPrice;
        this.rating = rating;
    }

    public Restaurant(){}

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

    public BigDecimal getMeanPrice() {
        return meanPrice;
    }

    public void setMeanPrice(BigDecimal price_range) {
        this.meanPrice = price_range;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", category='" + category + '\'' +
            ", address='" + address + '\'' +
            ", priceRange=" + meanPrice +
            ", rating=" + rating +
            '}';
    }
}
