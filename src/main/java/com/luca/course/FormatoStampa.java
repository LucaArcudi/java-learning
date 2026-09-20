package com.luca.course;

final public class FormatoStampa {

    private final Dimensioni dimensioni;

    public FormatoStampa(Dimensioni dimensioni) {
        this.dimensioni = new Dimensioni(dimensioni.getLarghezza(), dimensioni.getLunghezza());
    }

    public Dimensioni getDimensioni() {
        return new Dimensioni(dimensioni.getLarghezza(), dimensioni.getLunghezza());
    }
}
