package com.luca.course;

public record Address(String city, String country) {

    public Address {
        city = city.trim();
        country = country.trim();
    }

    public Address withCity(String newCity) {

        Address newAddress = new Address(newCity, this.country);
        return newAddress;
    }

    public String format() {
        return this.city + ", " + this.country;
    }
}
