package com.luca.course;

public class Address {

    private String city;
    private String country;

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String format() {
        return this.city + ", " + this.country;
    }
}
