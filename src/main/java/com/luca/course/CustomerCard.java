package com.luca.course;

final public class CustomerCard {
    private final String customer;

    public CustomerCard(Customer customer) {
        this.customer = customer.getFullName();
    }

    public String getCustomerName() {
        return this.customer;
    }
}
