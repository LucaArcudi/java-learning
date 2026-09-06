# OOD — promemoria rapido

## Prima di scrivere codice

- Descrivi il caso d'uso e il suo flusso senza pensare subito a classi o costrutti Java.
- Per ogni decisione chiediti quale oggetto possiede lo stato necessario per prenderla.
- Chiarisci responsabilità, relazioni e confini; implementa soltanto quando il flusso è spiegabile senza ambiguità.

## Stato e comportamento

- Metti una regola vicino ai dati da cui dipende: `prodotto.prenota(quantita)` è preferibile a leggere lo stock e modificarlo dall'esterno.
- Chiedi agli oggetti di agire, invece di estrarre i loro dati per lavorare al posto loro (*tell, don't ask*).
- Mantieni lo stato `private` e permetti di cambiarlo soltanto tramite operazioni che proteggono le regole dell'oggetto.
- Costruisci oggetti validi fin dall'inizio e rappresenta i cambiamenti di stato con operazioni esplicite come `conferma()` o `concludi()`.
- Quando possibile, controllo e modifica devono essere una sola operazione: `prenota()` verifica e aggiorna lo stock.

## Classi e relazioni

- Crea una classe quando esiste una responsabilità reale oppure qualcosa con stato, identità o ciclo di vita; non trasformare automaticamente ogni verbo in una classe.
- Usa la composizione per una relazione *has-a* e per comportamenti che possono cambiare.
- Usa l'ereditarietà soltanto per una vera relazione *is-a*, quando tutto ciò che offre la superclasse ha senso per ogni sottoclasse.
- Crea un'interfaccia soltanto per una capacità realmente sostituibile; ogni metodo deve avere significato per tutte le implementazioni.
- Il polimorfismo è utile quando il chiamante usa un comportamento comune senza conoscere o controllare il tipo concreto.

## Collaborazione tra oggetti

- Un oggetto composto può chiedere a un collaboratore di agire, per esempio `this.utente.registraPrestito()`, ma non può accedere al suo stato privato.
- Conserva come campo un collaboratore stabile; passalo al singolo metodo se serve soltanto per quell'operazione.
- Se un collaboratore deve essere sostituibile, ricevilo dall'esterno tramite il suo contratto invece di costruire internamente una classe concreta.
- Evita lunghe catene di getter: esponi un'operazione significativa senza rivelare tutta la struttura interna.

## Servizi e punto di ingresso

- Un servizio è giustificato quando coordina più oggetti indipendenti o governa un vero flusso; le regole sullo stato restano negli oggetti che lo possiedono.
- Se un servizio inoltra soltanto una chiamata, probabilmente non aggiunge alcuna responsabilità.
- `main` crea e collega gli oggetti, avvia il caso d'uso e mostra il risultato; non dovrebbe contenere le regole di dominio.
- Cerca classi con responsabilità coese e pochi dettagli sugli altri oggetti.

## Segnali di progettazione forzata

- Getter e setter usati dal chiamante per applicare una regola che appartiene all'oggetto.
- Metodi o override che restituiscono valori fittizi come `0`, `false`, `null` o `""` perché non hanno senso per quel tipo.
- Classi `Servizio` o `Gestore` che aggiungono soltanto un passaggio.
- Ereditarietà scelta unicamente per riutilizzare codice.
- Regole di dominio, calcoli o controlli di stato concentrati nel `main`.

## Checklist essenziale

1. Chi possiede i dati e chi deve proteggere la regola?
2. Sto dicendo a un oggetto di agire o sto lavorando al posto suo?
3. La nuova classe ha una responsabilità propria?
4. La relazione è davvero *is-a*, *has-a* oppure una collaborazione temporanea?
5. Interfacce e superclassi contengono soltanto comportamenti validi per tutti i tipi coinvolti?
6. Ogni oggetto nasce valido e controlla le proprie transizioni di stato?
7. Riesco a raccontare l'intero flusso indicando chiaramente chi prende ogni decisione?

Regola finale: ogni classe, relazione e comportamento dovrebbe corrispondere a una responsabilità reale del dominio.
