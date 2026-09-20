package com.luca.course;

public sealed interface EsitoStampa permits StampaAvviata, StampaRifiutata {

    String descrizione();
}