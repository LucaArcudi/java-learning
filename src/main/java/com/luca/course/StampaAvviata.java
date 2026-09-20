package com.luca.course;

public record StampaAvviata(String codiceStampante) implements EsitoStampa {
    @Override
    public String descrizione() {
        return "Stampante " + codiceStampante + " avviata";
    }
}
