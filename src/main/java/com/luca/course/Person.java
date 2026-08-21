package com.luca.course;

public abstract class Person {

    private String name;
    private String surname;
    private Address address;
    private static int createdPeople = 0;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        createdPeople = createdPeople + 1;
    }

    public Person(String name) {
        this(name, "N/D");
    }

    public void rename(String name) {
        this.name = name;
    }

    public void rename(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    protected String getFullName() {
        return this.name + " " + this.surname;
    }

    public static int getCreatedPeople() {
        return createdPeople;
    }

    public void moveTo(Address newAddress) {
        this.address = newAddress;
    }

    public String getFormattedAddress() {
        if (this.address == null) {
            return "Indirizzo assente";
        } else {
            return this.address.format();
        }
    }

    public abstract String getDescription();

}