package com.luca.exercises.exercise012.printshop.aireview;

public class PrintshopExercise {
    private static int verificheFallite;

    public static void main(String[] args) {
        verificaPreventivoERifornimento();
        verificaAvvioECompletamento();
        verificaAnnullamento();

        System.out.println("Verifiche fallite: " + verificheFallite);
    }

    private static void verificaPreventivoERifornimento() {
        ModalitaStampa fronte = new Fronte();
        ModalitaStampa fronteRetro = new FronteRetro();
        ScortaFogli scorta = new ScortaFogli(0);
        CentroStampa centro = new CentroStampa(scorta);
        Stampante stampante = new Stampante("P1", true);
        Commessa commessa = new Commessa("C1", "Luca", 5, 3, fronte);

        verifica("5 pagine, 3 copie fronte: 15 fogli e 150 centesimi",
                commessa.getFogliNecessari() == 15 && commessa.getPrezzoTotale() == 150);
        verifica("Cambio modalità prima dell'avvio", commessa.cambiaModalita(fronteRetro));
        verifica("Stessa commessa, fronte-retro: 9 fogli e 144 centesimi",
                "C1".equals(commessa.getCodice())
                        && commessa.getFogliNecessari() == 9 && commessa.getPrezzoTotale() == 144);
        verifica("Preventivi e cambio modalità non impegnano risorse",
                scorta.getDisponibili() == 0 && stampante.isLibera()
                        && commessa.puoEssereAvviata() && commessa.getStampanteInUso() == null);

        Commessa paginePari = new Commessa("C2", "Anna", 4, 3, fronteRetro);
        Commessa paginaSingola = new Commessa("C3", "Sara", 1, 3, fronteRetro);
        verifica("Fronte-retro con pagine pari",
                paginePari.getFogliNecessari() == 6 && paginePari.getPrezzoTotale() == 96);
        verifica("Ogni copia da una pagina usa un foglio nuovo",
                paginaSingola.getFogliNecessari() == 3 && paginaSingola.getPrezzoTotale() == 48);

        verifica("Completamento prima dell'avvio rifiutato", !centro.completa(commessa));
        verifica("Rifornimento nullo rifiutato", !scorta.aggiungi(0));
        verifica("Rifornimento negativo rifiutato", !scorta.aggiungi(-2));
        verifica("Scorta invariata dopo rifornimenti rifiutati", scorta.getDisponibili() == 0);
        verifica("Rifornimento parziale", scorta.aggiungi(8));
        verifica("Avvio con carta insufficiente rifiutato", !centro.avvia(commessa, stampante));
        verifica("Il rifiuto non modifica commessa, macchina o carta",
                commessa.puoEssereAvviata() && commessa.getStampanteInUso() == null
                        && stampante.isLibera() && scorta.getDisponibili() == 8);

        verifica("Rifornimento del foglio mancante", scorta.aggiungi(1));
        verifica("Riprova della stessa commessa con carta esatta", centro.avvia(commessa, stampante));
        verifica("Carta consumata e associazione nei due oggetti",
                scorta.getDisponibili() == 0 && commessa.isInStampa()
                        && commessa.getStampanteInUso() == stampante
                        && stampante.getCommessaInEsecuzione() == commessa && !stampante.isLibera());
    }

    private static void verificaAvvioECompletamento() {
        ModalitaStampa fronte = new Fronte();
        ModalitaStampa fronteRetro = new FronteRetro();
        ScortaFogli scorta = new ScortaFogli(40);
        CentroStampa centro = new CentroStampa(scorta);
        Stampante primaStampante = new Stampante("P1", true);
        Stampante secondaStampante = new Stampante("P2", true);
        Stampante guasta = new Stampante("P3", false);
        Commessa prima = new Commessa("C1", "Luca", 5, 3, fronteRetro);
        Commessa seconda = new Commessa("C2", "Anna", 2, 2, fronte);
        Commessa terza = new Commessa("C3", "Sara", 1, 1, fronte);

        verifica("Avvio su stampante fuori servizio rifiutato", !centro.avvia(prima, guasta));
        verifica("Rifiuto senza cercare altre macchine né consumare carta",
                scorta.getDisponibili() == 40 && prima.puoEssereAvviata()
                        && guasta.isLibera() && !guasta.isOperativa()
                        && primaStampante.isLibera() && secondaStampante.isLibera());
        verifica("Avvio sulla macchina scelta", centro.avvia(prima, primaStampante));
        verifica("Doppio avvio sulla stessa macchina rifiutato", !centro.avvia(prima, primaStampante));
        verifica("Doppio avvio su un'altra macchina rifiutato", !centro.avvia(prima, secondaStampante));
        verifica("Altra commessa su macchina occupata rifiutata", !centro.avvia(seconda, primaStampante));
        verifica("I rifiuti lasciano carta, associazioni e seconda commessa invariati",
                scorta.getDisponibili() == 31 && prima.isInStampa()
                        && prima.getStampanteInUso() == primaStampante
                        && primaStampante.getCommessaInEsecuzione() == prima
                        && secondaStampante.isLibera() && seconda.puoEssereAvviata());

        verifica("Cambio modalità durante stampa rifiutato", !prima.cambiaModalita(fronte));
        verifica("Annullamento durante stampa rifiutato", !prima.annulla());
        verifica("Preventivo bloccato e commessa ancora in stampa",
                prima.isInStampa() && prima.getFogliNecessari() == 9
                        && prima.getPrezzoTotale() == 144 && scorta.getDisponibili() == 31);

        verifica("Seconda commessa su seconda stampante", centro.avvia(seconda, secondaStampante));
        verifica("Due stampanti consumano dalla stessa scorta",
                scorta.getDisponibili() == 27 && primaStampante.getCommessaInEsecuzione() == prima
                        && secondaStampante.getCommessaInEsecuzione() == seconda);
        verifica("Completamento prima commessa", centro.completa(prima));
        verifica("Completamento libera solo la macchina corretta, senza cambiare carta",
                "Completata".equals(prima.getStato()) && prima.getStampanteInUso() == null
                        && primaStampante.isLibera() && seconda.isInStampa()
                        && secondaStampante.getCommessaInEsecuzione() == seconda
                        && scorta.getDisponibili() == 27);

        verifica("Riuso della stampante liberata", centro.avvia(terza, primaStampante));
        verifica("Vecchio completamento ripetuto rifiutato", !centro.completa(prima));
        verifica("Il vecchio completamento non libera la nuova commessa",
                terza.isInStampa() && terza.getStampanteInUso() == primaStampante
                        && primaStampante.getCommessaInEsecuzione() == terza
                        && scorta.getDisponibili() == 26);
        verifica("Completamento seconda commessa", centro.completa(seconda));
        verifica("Riavvio di una commessa completata rifiutato", !centro.avvia(prima, secondaStampante));
        verifica("Annullamento di una commessa completata rifiutato", !prima.annulla());
        verifica("Cambio modalità dopo completamento rifiutato", !prima.cambiaModalita(fronte));
        verifica("Commessa completata consultabile e risorse invariate",
                "Completata".equals(prima.getStato()) && "Luca".equals(prima.getCliente())
                        && prima.getFogliNecessari() == 9 && prima.getPrezzoTotale() == 144
                        && secondaStampante.isLibera() && scorta.getDisponibili() == 26);
    }

    private static void verificaAnnullamento() {
        ScortaFogli scorta = new ScortaFogli(10);
        CentroStampa centro = new CentroStampa(scorta);
        Stampante stampante = new Stampante("P1", true);
        Commessa commessa = new Commessa("C1", "Luca", 2, 1, new Fronte());

        verifica("Annullamento prima dell'avvio", commessa.annulla());
        verifica("Annullamento ripetuto rifiutato", !commessa.annulla());
        verifica("Avvio dopo annullamento rifiutato", !centro.avvia(commessa, stampante));
        verifica("Completamento dopo annullamento rifiutato", !centro.completa(commessa));
        verifica("Cambio modalità dopo annullamento rifiutato", !commessa.cambiaModalita(new FronteRetro()));
        verifica("Commessa annullata consultabile, nessuna risorsa consumata",
                "Annullata".equals(commessa.getStato()) && commessa.getStampanteInUso() == null
                        && commessa.getFogliNecessari() == 2 && commessa.getPrezzoTotale() == 20
                        && stampante.isLibera() && scorta.getDisponibili() == 10);
    }

    private static void verifica(String descrizione, boolean condizione) {
        if (condizione) {
            System.out.println("OK: " + descrizione);
        } else {
            verificheFallite++;
            System.out.println("ERRORE: " + descrizione);
        }
    }
}
