package org.dstu.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "airplane")
public class Airplane {
    private int id;
    private String brand;
    private String model;
    private int numberOfSeats;

    private Airport airport;
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "number_of_seats")
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_id")
    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return id == airplane.id && Objects.equals(brand, airplane.brand) && Objects.equals(model, airplane.model) && Objects.equals(numberOfSeats, airplane.numberOfSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, numberOfSeats);
    }
}
