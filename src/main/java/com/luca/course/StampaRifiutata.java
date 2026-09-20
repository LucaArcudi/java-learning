package com.luca.course;

public record StampaRifiutata(String motivo) implements EsitoStampa {
    @Override
    public String descrizione() {
        return "Stampa rifiutata: " + motivo;
    }
}
