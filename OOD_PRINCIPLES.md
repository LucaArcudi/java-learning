# Principi OOD — guida pratica

Questi principi servono come guida per decidere dove collocare stato e comportamento quando si progetta un piccolo modello a oggetti.

Non sono regole assolute. Sono domande e segnali utili per individuare responsabilità confuse o relazioni forzate.

## 1. Assegna il comportamento all'oggetto che possiede i dati

Se una regola dipende principalmente dallo stato di un oggetto, generalmente dovrebbe essere quell'oggetto ad applicarla.

Codice che estrae dati e lavora al posto dell'oggetto:

```java
double totale = prenotazione.getNumeroOre()
        * prenotazione.getTariffaOraria();
```

Comportamento assegnato alla prenotazione:

```java
double totale = prenotazione.calcolaTotale();
```

Nel secondo caso il chiamante non deve conoscere la formula né i dettagli dei diversi tipi di prenotazione.

Domanda utile:

> Quale oggetto possiede le informazioni necessarie per prendere questa decisione?

## 2. Chiedi all'oggetto di agire, non di consegnarti tutti i suoi dati

Un oggetto non dovrebbe essere soltanto un contenitore di campi con getter e setter.

Approccio debole:

```java
if (prodotto.getQuantitaDisponibile() >= quantita) {
    prodotto.setQuantitaDisponibile(
            prodotto.getQuantitaDisponibile() - quantita);
}
```

Approccio orientato al comportamento:

```java
boolean riuscita = prodotto.prenota(quantita);
```

Il chiamante esprime l'intenzione. `Prodotto` decide come applicarla.

Questo principio viene spesso riassunto come:

> Tell, don't ask: comunica all'oggetto cosa vuoi ottenere, invece di interrogarlo per fare il lavoro al suo posto.

## 3. Incapsulare significa proteggere lo stato valido

Rendere i campi `private` è soltanto il primo passo. L'API pubblica deve impedire che l'oggetto venga portato in uno stato non valido.

Esempio:

```java
public class Prodotto {

    private int quantitaDisponibile;
    private boolean attivo;

    public Prodotto(int quantitaDisponibile, boolean attivo) {
        this.quantitaDisponibile = quantitaDisponibile;
        this.attivo = attivo;
    }

    public boolean prenota(int quantita) {
        if (!this.attivo) {
            return false;
        }

        if (quantita <= 0) {
            return false;
        }

        if (quantita > this.quantitaDisponibile) {
            return false;
        }

        this.quantitaDisponibile =
                this.quantitaDisponibile - quantita;

        return true;
    }

    public int getQuantitaDisponibile() {
        return this.quantitaDisponibile;
    }
}
```

Non esiste un setter pubblico per lo stock. L'unico modo per ridurlo applica tutte le regole.

Una condizione che deve rimanere sempre vera, come «lo stock non può essere negativo», è un'invariante dell'oggetto.

## 4. Controllo e modifica dovrebbero formare un'unica operazione

Separare la verifica dalla modifica permette al chiamante di eseguire soltanto metà del procedimento.

Approccio fragile:

```java
if (prodotto.puoPrenotare(quantita)) {
    prodotto.riduciStock(quantita);
}
```

Approccio più sicuro:

```java
boolean riuscita = prodotto.prenota(quantita);
```

`prenota()` controlla le condizioni e modifica lo stato soltanto quando può completare l'operazione.

## 5. Non trasformare automaticamente ogni verbo in una classe

Una semplice operazione sullo stato di un solo oggetto può essere un metodo.

Per prenotare una quantità di prodotto non servono necessariamente:

```text
PrenotazioneStock
ServizioPrenotazioneStock
GestorePrenotazioneStock
```

Può bastare:

```java
boolean riuscita = prodotto.prenota(quantita);
```

Una nuova classe deve avere una responsabilità o uno stato propri, non soltanto inoltrare una chiamata.

## 6. Un'operazione può diventare un oggetto se ha identità e ciclo di vita

Un prestito bibliotecario non è soltanto l'azione iniziale di prestare un libro. Continua a esistere, possiede un codice e può passare da attivo a concluso.

In questo caso `Prestito` è un oggetto sensato:

```java
public class Prestito {

    private String codice;
    private Utente utente;
    private CopiaLibro copia;
    private boolean attivo;

    public Prestito(
            String codice,
            Utente utente,
            CopiaLibro copia) {
        this.codice = codice;
        this.utente = utente;
        this.copia = copia;
        this.attivo = true;
    }

    public boolean concludi() {
        if (!this.attivo) {
            return false;
        }

        this.attivo = false;
        return true;
    }
}
```

Domande utili:

- Il risultato dell'operazione deve continuare a esistere?
- Ha un'identità propria?
- Il suo stato cambia nel tempo?
- Altri comportamenti futuri agiranno su di esso?

Se le risposte sono positive, una classe dedicata può essere giustificata.

## 7. Un servizio serve quando coordina più oggetti indipendenti

Un bonifico coinvolge due conti. Nessuno dei due rappresenta da solo l'intera operazione.

Ogni conto protegge il proprio saldo:

```java
public class Conto {

    private double saldo;
    private boolean attivo;

    public boolean addebita(double importo) {
        if (!this.attivo || importo <= 0 || importo > this.saldo) {
            return false;
        }

        this.saldo = this.saldo - importo;
        return true;
    }

    public boolean accredita(double importo) {
        if (!this.puoRicevere(importo)) {
            return false;
        }

        this.saldo = this.saldo + importo;
        return true;
    }

    public boolean puoRicevere(double importo) {
        return this.attivo && importo > 0;
    }
}
```

Un servizio coordina l'interazione:

```java
public class ServizioBonifico {

    public boolean trasferisci(
            Conto sorgente,
            Conto destinatario,
            double importo) {
        if (!destinatario.puoRicevere(importo)) {
            return false;
        }

        boolean addebitoRiuscito = sorgente.addebita(importo);

        if (!addebitoRiuscito) {
            return false;
        }

        return destinatario.accredita(importo);
    }
}
```

Il servizio non modifica direttamente i campi dei conti. Coordina i loro comportamenti pubblici.

Questo esempio ignora volutamente persistenza, transazioni e recupero da errori: sono argomenti successivi.

## 8. Un servizio centralizza il flusso, non tutte le regole

Un servizio non dovrebbe estrarre lo stato degli oggetti e appropriarsi delle loro decisioni.

Nel rinnovo di un abbonamento:

```text
Abbonamento
├── sa se può essere rinnovato
└── calcola il prezzo

MetodoPagamento
└── prova a pagare un importo

ServizioRinnovo
└── coordina Abbonamento e MetodoPagamento
```

Schema tecnico:

```java
public class ServizioRinnovo {

    private MetodoPagamento metodoPagamento;

    public ServizioRinnovo(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public boolean rinnova(Abbonamento abbonamento) {
        if (!abbonamento.puoEssereRinnovato()) {
            return false;
        }

        double totale = abbonamento.calcolaPrezzoRinnovo();
        return this.metodoPagamento.paga(totale);
    }
}
```

Il servizio decide l'ordine delle operazioni. `Abbonamento` conserva le regole sul proprio stato e `MetodoPagamento` conserva la responsabilità del pagamento.

## 9. Non creare un servizio che si limita a inoltrare una chiamata

Questo servizio non aggiunge una responsabilità:

```java
public class ServizioPrenotazione {

    public boolean prenota(Prodotto prodotto, int quantita) {
        return prodotto.prenota(quantita);
    }
}
```

Se non coordina altri oggetti, non gestisce un flusso e non applica una decisione propria, può essere eliminato.

Domanda utile:

> Se rimuovo questa classe, perdo una responsabilità oppure soltanto un passaggio?

## 10. Crea un'interfaccia quando esiste una variazione reale

Se un'applicazione può pagare con carta o bonifico, esiste una capacità realmente sostituibile:

```java
public interface MetodoPagamento {

    boolean paga(double importo);
}
```

```java
public class Carta implements MetodoPagamento {

    @Override
    public boolean paga(double importo) {
        return true;
    }
}
```

```java
public class Bonifico implements MetodoPagamento {

    @Override
    public boolean paga(double importo) {
        return true;
    }
}
```

Il servizio dipende dal contratto:

```java
private MetodoPagamento metodoPagamento;
```

Non deve conoscere tutte le classi concrete con una catena di `if`.

Un'interfaccia con una sola implementazione non è automaticamente sbagliata, ma deve rappresentare una variazione o un confine reale. Non va creata soltanto perché esiste un verbo.

## 11. Un contratto deve contenere soltanto capacità comuni

Carta e bonifico possono entrambi pagare, ma soltanto la carta supporta un rimborso automatico.

Non conviene obbligare il bonifico a implementare un metodo senza significato:

```java
public boolean rimborsa(double importo) {
    return false;
}
```

È più coerente separare le capacità:

```java
public interface MetodoPagamento {

    boolean paga(double importo);
}
```

```java
public interface Rimborsabile {

    boolean rimborsa(double importo);
}
```

```java
public class Carta implements MetodoPagamento, Rimborsabile {

    @Override
    public boolean paga(double importo) {
        return true;
    }

    @Override
    public boolean rimborsa(double importo) {
        return true;
    }
}
```

```java
public class Bonifico implements MetodoPagamento {

    @Override
    public boolean paga(double importo) {
        return true;
    }
}
```

Se una classe deve restituire sistematicamente `0`, `false`, `null` o una stringa vuota perché un metodo non la riguarda, probabilmente il contratto è troppo ampio.

## 12. Una superclasse deve descrivere soltanto ciò che è comune

Una classe astratta `Prenotazione` non dovrebbe dichiarare contemporaneamente:

```text
getSupplementoMonitor()
getCostoPreparazioneSala()
```

Questo obbligherebbe ogni sottoclasse a conoscere proprietà che appartengono agli altri tipi.

La superclasse può invece dichiarare comportamenti comuni con risultati specializzati:

```java
public abstract class Prenotazione {

    public abstract double calcolaTotale();

    public abstract String generaDescrizione();
}
```

Entrambe le operazioni hanno significato per ogni prenotazione. Le sottoclassi possono implementarle usando il proprio stato.

## 13. Il polimorfismo deve esporre comportamenti significativi

Il polimorfismo non consiste soltanto nel dichiarare una variabile con il tipo padre:

```java
Prenotazione prenotazione = new PrenotazioneSala(...);
```

Diventa utile quando il chiamante usa un comportamento comune senza conoscere il tipo reale:

```java
System.out.println(prenotazione.calcolaTotale());
System.out.println(prenotazione.generaDescrizione());
```

L'oggetto concreto decide quale override eseguire.

## 14. Composizione non significa accesso libero allo stato interno

Se `Prestito` contiene un `Utente`, può chiamare i suoi metodi accessibili:

```java
public class Prestito {

    private Utente utente;

    public Prestito(Utente utente) {
        this.utente = utente;
    }

    public boolean registraInizio() {
        return this.utente.registraPrestito();
    }
}
```

`Prestito` non accede direttamente ai campi privati di `Utente`. È sempre `Utente` a proteggere e modificare il proprio stato:

```java
public class Utente {

    private int prestitiAttivi;

    public boolean registraPrestito() {
        if (this.prestitiAttivi >= 3) {
            return false;
        }

        this.prestitiAttivi++;
        return true;
    }
}
```

Tecnicamente è quindi possibile scrivere, dentro `Prestito`:

```java
this.utente.registraPrestito();
```

Non dovrebbe invece essere esposto il campo per permettere al codice esterno di attraversarlo liberamente:

```java
prestito.utente.registraPrestito();
```

La composizione permette collaborazione e delega; non elimina i confini di incapsulamento.

## 15. Distingui proprietà stabile e collaborazione temporanea

Una modalità di consegna può essere una parte stabile e sostituibile di un ordine:

```java
public class Ordine {

    private ModalitaConsegna modalitaConsegna;

    public Ordine(ModalitaConsegna modalitaConsegna) {
        this.modalitaConsegna = modalitaConsegna;
    }

    public void cambiaModalitaConsegna(
            ModalitaConsegna nuovaModalita) {
        this.modalitaConsegna = nuovaModalita;
    }
}
```

Due conti coinvolti in un bonifico, invece, possono essere ricevuti soltanto per quella specifica operazione:

```java
public boolean trasferisci(
        Conto sorgente,
        Conto destinatario,
        double importo) {
    // coordinamento dell'operazione
}
```

Non ogni oggetto utilizzato da un metodo deve diventare un campo della classe.

## 16. Il punto di ingresso avvia il caso d'uso, non contiene il dominio

Il `main` dovrebbe principalmente:

- creare gli oggetti;
- collegare le dipendenze;
- avviare il caso d'uso;
- mostrare il risultato dell'esempio.

Segnale di responsabilità mal collocate:

```java
double totale = ore * tariffa + supplemento;

if (disponibile) {
    // costruzione manuale del comportamento specifico
}
```

Uso più coerente:

```java
double totale = prenotazione.calcolaTotale();
String istruzioni = gestoreAccesso.preparaIstruzioni(prenotazione);
```

## 17. Costruisci oggetti validi fin dall'inizio

Un costruttore dovrebbe ricevere lo stato indispensabile per rendere immediatamente utilizzabile l'oggetto.

Questa costruzione permette di dimenticare un passaggio obbligatorio:

```java
Ordine ordine = new Ordine();
ordine.setCodice("ORD-101");
ordine.setSubtotale(80.0);
```

Una costruzione più sicura richiede subito i dati essenziali:

```java
Ordine ordine = new Ordine("ORD-101", 80.0);
```

Lo stato facoltativo può essere aggiunto successivamente attraverso operazioni significative. Per esempio, un ordine può nascere non confermato e diventare confermato tramite `conferma()`.

Il costruttore non dovrebbe però diventare un coordinatore nascosto: inizializzare il proprio oggetto è diverso da effettuare pagamenti, inviare messaggi o modificare altri oggetti.

Domande utili:

- Quali dati sono indispensabili affinché l'oggetto abbia senso?
- Dopo il costruttore l'oggetto può già essere usato correttamente?
- Esiste una sequenza di setter che tutti i chiamanti devono ricordarsi?

## 18. Modella le transizioni di stato come operazioni esplicite

Quando un oggetto cambia fase, la modifica dovrebbe passare attraverso un comportamento che controlla la transizione.

Un ordine può cambiare modalità di consegna soltanto prima della conferma:

```java
public class Ordine {

    private boolean confermato;
    private ModalitaConsegna modalitaConsegna;

    public boolean cambiaModalitaConsegna(
            ModalitaConsegna nuovaModalita) {
        if (this.confermato || nuovaModalita == null) {
            return false;
        }

        this.modalitaConsegna = nuovaModalita;
        return true;
    }

    public boolean conferma() {
        if (this.confermato) {
            return false;
        }

        this.confermato = true;
        return true;
    }
}
```

Il chiamante non controlla il flag per poi modificarlo. Chiede all'ordine di eseguire la transizione, e l'ordine protegge la regola.

Esempi di transizioni:

```text
bozza → confermato
attivo → bloccato
prestito attivo → prestito concluso
disponibile → prenotato
```

## 19. Cerca alta coesione e basso accoppiamento

La **coesione** indica quanto le responsabilità di una classe sono collegate tra loro.

Una `Fattura` che conserva imponibile, calcola IVA e calcola totale ha responsabilità coese: tutte riguardano il proprio valore economico.

Una `Fattura` che calcola IVA, apre una connessione email, genera credenziali digitali e modifica lo stock conosce invece parti troppo diverse del sistema.

L'**accoppiamento** indica quanti dettagli esterni una classe deve conoscere.

Accoppiamento elevato:

```java
public class ServizioRinnovo {

    private Carta carta = new Carta();

    public boolean rinnova(Abbonamento abbonamento) {
        return this.carta.paga(
                abbonamento.calcolaPrezzoRinnovo());
    }
}
```

`ServizioRinnovo` decide direttamente quale implementazione usare. Per sostituire la carta con un bonifico bisogna modificare la classe.

Domande utili:

- Le operazioni della classe lavorano sullo stesso scopo?
- Quanti dettagli di altri oggetti deve conoscere?
- Una modifica esterna costringe a modificare anche questa classe?
- Il nome della classe descrive ancora tutte le sue responsabilità?

## 20. Ricevi i collaboratori invece di costruirli internamente

Se un comportamento deve essere sostituibile, la classe che lo usa dovrebbe riceverlo dall'esterno tramite il suo contratto.

```java
public class ServizioRinnovo {

    private MetodoPagamento metodoPagamento;

    public ServizioRinnovo(
            MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public boolean rinnova(Abbonamento abbonamento) {
        double totale = abbonamento.calcolaPrezzoRinnovo();
        return this.metodoPagamento.paga(totale);
    }
}
```

Il punto di ingresso decide la composizione:

```java
MetodoPagamento pagamento = new Carta();
ServizioRinnovo servizio = new ServizioRinnovo(pagamento);
```

`ServizioRinnovo` conosce ciò che il collaboratore sa fare, ma non è legato alla classe concreta che lo realizza.

Questo è semplice passaggio di oggetti tramite costruttore: non richiede framework o meccanismi ulteriori.

## 21. Usa l'ereditarietà soltanto per una vera relazione *is-a*

L'ereditarietà non dovrebbe essere scelta soltanto per riutilizzare qualche riga di codice.

Domanda fondamentale:

> Ogni oggetto della sottoclasse può essere considerato realmente un oggetto della superclasse?

Se `PrenotazioneSala` estende `Prenotazione`, ogni sala prenotata è effettivamente una prenotazione e può offrire tutti i comportamenti comuni previsti dal tipo astratto.

La modalità di consegna, invece, non crea tipi diversi di ordine. Lo stesso ordine può cambiare modalità senza perdere la propria identità:

```java
public class Ordine {

    private ModalitaConsegna modalitaConsegna;

    public void cambiaModalitaConsegna(
            ModalitaConsegna nuovaModalita) {
        this.modalitaConsegna = nuovaModalita;
    }
}
```

Qui la composizione rappresenta meglio la relazione:

```text
Ordine has-a ModalitaConsegna
```

Segnali di un'ereditarietà forzata:

- la sottoclasse eredita metodi che non hanno significato;
- alcuni override restituiscono valori fittizi;
- una caratteristica dovrebbe poter cambiare durante la vita dell'oggetto;
- il legame serve soltanto a condividere codice;
- il chiamante deve verificare continuamente quale sottoclasse sta usando.

## 22. Evita di esporre l'intero percorso interno degli oggetti

Una catena di chiamate può obbligare il chiamante a conoscere la struttura interna di diversi oggetti:

```java
String citta = ordine
        .getCliente()
        .getIndirizzo()
        .getCitta();
```

Il chiamante ora sa che:

- `Ordine` contiene un cliente;
- il cliente contiene un indirizzo;
- l'indirizzo espone una città.

Se questa struttura cambia, deve cambiare anche il chiamante.

Quando il chiamante ha bisogno di un'informazione significativa per l'ordine, si può esporre un comportamento più diretto:

```java
String destinazione = ordine.getDestinazioneConsegna();
```

L'ordine può delegare internamente senza rendere pubblico tutto il percorso:

```java
public String getDestinazioneConsegna() {
    return this.cliente.getIndirizzoConsegna();
}
```

Non ogni catena di metodi è automaticamente sbagliata. Il problema nasce quando molti chiamanti dipendono da dettagli interni che non appartengono alla loro responsabilità.

## 23. Progetta il flusso prima delle classi

Prima di scrivere file Java, conviene descrivere il caso d'uso con frasi del dominio e assegnare ogni decisione a un proprietario.

Un possibile ordine di lavoro è:

1. elencare i comportamenti richiesti senza nominare costrutti Java;
2. individuare chi possiede lo stato necessario per ogni comportamento;
3. distinguere oggetti con identità e ciclo di vita da semplici operazioni;
4. individuare le operazioni che coinvolgono più oggetti indipendenti;
5. distinguere relazioni *is-a*, *has-a* e collaborazioni temporanee;
6. cercare comportamenti realmente sostituibili;
7. definire l'API pubblica minima di ogni oggetto;
8. verificare che ogni metodo comune abbia senso per tutti i tipi coinvolti;
9. simulare a parole il flusso completo;
10. scrivere il codice soltanto quando non servono valori o metodi fittizi.

Per ogni oggetto si può compilare questa scheda:

```text
Nome:
Stato posseduto:
Responsabilità:
Operazioni pubbliche:
Collaboratori stabili:
Collaboratori ricevuti per una singola operazione:
Cose che non deve fare:
```

Esempio sintetico:

```text
Nome: Prodotto
Stato posseduto: codice, quantità disponibile, stato attivo
Responsabilità: proteggere e modificare il proprio stock
Operazioni pubbliche: prenota quantità
Collaboratori stabili: nessuno
Collaboratori temporanei: nessuno
Cose che non deve fare: pagamento, notifica, coordinamento di altri oggetti
```

La progettazione è sufficientemente chiara quando si riesce a raccontare il flusso indicando chi prende ogni decisione, senza frasi come «poi il main recupera tutti i dati e fa il calcolo».

## Checklist prima di aggiungere una classe o un metodo

Prima di decidere la struttura, chiediti:

1. Quale oggetto possiede i dati necessari?
2. Quale oggetto deve proteggere questa regola?
3. Sto chiedendo dati per lavorare al posto dell'oggetto?
4. Il comportamento coinvolge un solo oggetto o ne coordina diversi?
5. La nuova classe possiede una responsabilità reale?
6. L'interfaccia rappresenta implementazioni realmente sostituibili?
7. Ogni implementazione può dare un significato corretto a tutti i metodi del contratto?
8. Ogni metodo della superclasse ha senso per tutte le sottoclassi?
9. Sto aggiungendo getter soltanto perché la logica si trova nel chiamante?
10. Sono comparsi valori fittizi come `0`, `false`, `null` o `""` per soddisfare un contratto?
11. Il `main` sta soltanto avviando il caso d'uso o sta applicando regole di dominio?
12. Se elimino un servizio, perdo una responsabilità oppure soltanto un passaggio?
13. L'oggetto è valido appena termina il costruttore?
14. Le transizioni di stato possono aggirare le regole?
15. La classe contiene responsabilità tra loro coerenti?
16. La classe costruisce direttamente un collaboratore concreto che dovrebbe essere sostituibile?
17. Sto usando ereditarietà per una relazione reale o soltanto per riutilizzare codice?
18. Il chiamante conosce una lunga catena di oggetti interni?
19. Riesco a descrivere il flusso completo prima di scrivere il codice?

## Regola conclusiva

Una scelta OOD è generalmente buona quando il costrutto tecnico coincide con una responsabilità reale.
