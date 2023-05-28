package org.dstu.domain;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "airport")
public class Airport {
    private int id;
    private String name;
    private String shortName;
    private String country;
    private String city;
    private Set<Airplane> airplanes = new LinkedHashSet<>();

    @Basic
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "short_name")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @OneToMany(mappedBy = "airport")
    public Set<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(Set<Airplane> staff) {
        this.airplanes = staff;
    }
}
