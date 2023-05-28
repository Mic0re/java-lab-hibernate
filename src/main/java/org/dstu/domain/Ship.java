package org.dstu.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ship")
public class Ship {
    private int id;
    private String brand;
    private String model;
    private int maxCargo;

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
    @Column(name = "max_cargo")
    public int getMaxCargo() {
        return maxCargo;
    }

    public void setMaxCargo(int maxCargo) {
        this.maxCargo = maxCargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return id == ship.id && Objects.equals(brand, ship.brand) && Objects.equals(model, ship.model) && Objects.equals(maxCargo, ship.maxCargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, maxCargo);
    }
}
