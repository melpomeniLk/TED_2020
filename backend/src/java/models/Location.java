/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author alicemts
 */
public class Location {
    private long id;
    private String address;
    private String city;
    private String country;
    private String neighborhood;

    public Location() {
    }

    public Location(long id, String address, String city, String country, String neighborhood) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.country = country;
        this.neighborhood = neighborhood;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
    
    
    
    
}
