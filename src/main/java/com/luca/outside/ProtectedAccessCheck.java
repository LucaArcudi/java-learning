package com.luca.outside;

import com.luca.course.Person;

public class ProtectedAccessCheck extends Person {

    public ProtectedAccessCheck(String name, String surname) {
        super(name, surname);
    }

    @Override
    public String getDescription() {
        return getFullName();
    }

    public static void main(String[] args) {
        ProtectedAccessCheck person = new ProtectedAccessCheck("Luca", "Bianchi");

        System.out.println(person.getDescription());
    }

    public String describe(ProtectedAccessCheck person) {
        return person.getFullName();
    }
}