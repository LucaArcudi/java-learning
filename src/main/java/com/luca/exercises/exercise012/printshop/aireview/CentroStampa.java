package com.luca.exercises.exercise012.printshop.aireview;

public class CentroStampa {
    private ScortaFogli scorta;

    public CentroStampa(ScortaFogli scorta) {
        this.scorta = scorta;
    }

    public boolean avvia(Commessa commessa, Stampante stampante) {
        if (!commessa.puoEssereAvviata() || !stampante.isDisponibile()) {
            return false;
        }

        int fogli = commessa.getFogliNecessari();
        if (!this.scorta.preleva(fogli)) {
            return false;
        }

        // Tutti i possibili rifiuti sono stati verificati prima di impegnare gli oggetti.
        // La simulazione è sequenziale: tra controllo e modifica nessuno cambia lo stato.
        stampante.occupa(commessa);
        commessa.iniziaStampa(stampante);
        return true;
    }

    public boolean completa(Commessa commessa) {
        if (!commessa.isInStampa()) {
            return false;
        }

        Stampante stampante = commessa.getStampanteInUso();
        if (!stampante.libera(commessa)) {
            return false;
        }

        return commessa.terminaStampa();
    }
}
