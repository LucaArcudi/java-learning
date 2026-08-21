package com.luca.course;

public class Employee extends Person {

    private String role;

    public Employee(String name, String surname, String role) {
        super(name, surname);
        this.role = role;
    }

    public String getProfessionalDescription() {
        return getFullName() + " - " + this.role;
    }

    @Override
    public String getDescription() {
        return getFullName() + " - " + this.role;
    }
}
