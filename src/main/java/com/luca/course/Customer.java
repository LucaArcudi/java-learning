package com.luca.course;

public class Customer extends Person {
    private String customerCode;

    public Customer(String name, String surname, String customerCode) {
        super(name, surname);
        this.customerCode = customerCode;
    }

    @Override
    public String getDescription() {
        return getFullName() + " - " + this.customerCode;
    }
}
