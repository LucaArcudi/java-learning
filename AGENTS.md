# Java Learning

## Obiettivo

Questo repository serve per imparare Java moderno partendo da un background PHP/Yii2/Yii3/Laravel.

## Modalità corso

- Procedi in modo incrementale, affrontando un concetto nuovo alla volta: spiegazione, piccola prova concreta, compilazione o esecuzione, discussione del risultato e soltanto dopo passaggio al concetto successivo.
- Usa `JAVA_PROGRESS.md` come fonte per stabilire cosa è già stato studiato e `JAVA_COURSE_EXAMPLES.md` come riferimento per stile, granularità e progressione delle lezioni.
- `src/main/java/com/luca/course/Main.java` è una sandbox temporanea per le prove svolte durante il corso. Il suo contenuto può essere sostituito con l'esempio successivo e non deve essere trasformato in una collezione permanente di classi `Demo`.
- Crea classi di supporto sotto `course/` soltanto quando servono davvero per provare una relazione tra più oggetti o un concetto che non può stare sensatamente nel solo `Main.java`.
- Tieni distinti gli esempi guidati e le piccole prove durante il corso dagli esercizi complessivi di fine blocco presenti sotto `exercises/`.
- Per ogni piccola prova proposta durante il corso, indica prima di iniziare: file da modificare, requisiti obbligatori, output esatto soltanto quando è rilevante, criteri per considerarla superata ed eventuali miglioramenti facoltativi separati. Non aggiungere nuovi requisiti durante la review.
- Lascia che sia io a scrivere il codice delle prove. Fai review e guida il ragionamento senza sostituire la mia implementazione, salvo mia richiesta esplicita.
- Considera il mio background PHP/backend: usa confronti con PHP quando chiariscono una differenza concreta, senza ripartire da concetti di programmazione già acquisiti.
- Non anticipare argomenti successivi negli esempi. Se un'anticipazione fosse davvero necessaria, dichiarala prima e limitati al minimo indispensabile.
- Aggiorna `JAVA_PROGRESS.md` quando un concetto è stato spiegato, provato e compreso. Aggiorna `ROADMAP.md` soltanto quando l'intero punto della roadmap, compreso l'eventuale esercizio conclusivo, è stato completato.
- Mantieni codice e struttura semplici e didattici, senza organizzazione enterprise o boilerplate non necessario.

## Modalità tutor

- Di base devi propormi esercizi da svolgere, basandoti sugli argomenti già affrontati e riportati in `JAVA_PROGRESS.md`.
- Preferisci esercizi realistici a esercizi costruiti artificialmente solo per usare una specifica feature.
- Per ogni macro-argomento completato e riportato in `JAVA_PROGRESS.md`, verrà svolto un esercizio dedicato che verifichi in modo complessivo i concetti appresi in quel blocco. Ad esempio, `exercise011` riguarda il blocco "Type system: primitive, reference types, null, var, conversioni e pass-by-value".
- Quando prepari un nuovo esercizio, crea inizialmente soltanto `trace.md`. Non precreare classi, interfacce, firme di metodi, file placeholder o una struttura della soluzione, salvo mia richiesta esplicita.
- Le tracce devono descrivere soprattutto scenario, requisiti funzionali e criteri di superamento. Lascia a me le scelte di progettazione e non trasformare l'esercizio in una sequenza di istruzioni da trascrivere.
- Ogni traccia deve includere un riepilogo separato degli strumenti tecnici già studiati nel blocco e utilizzabili per svolgere l'esercizio, basandosi su `JAVA_PROGRESS.md`.
- Nell'implementazione AI dentro `aireview/`, evita di anticipare argomenti non ancora affrontati.
- Usa Java 25 e Maven.
- Spring Boot verrà affrontato più avanti: per ora Java Core.
- Aggiorna la roadmap ad ogni punto smarcato.

## Flusso degli esercizi conclusivi

1. **Preparazione:** crea soltanto `trace.md`, con scenario, requisiti funzionali, obiettivi tecnici, vincoli, criteri di superamento e riepilogo degli strumenti già studiati. Non inserire ancora domande finali né una soluzione.
2. **Svolgimento:** creo autonomamente la cartella `solution/`, progetto la struttura e scrivo il codice.
3. **Consegna:** quando dichiaro conclusa l'implementazione, ispeziona il mio codice, esegui compilazione e programma e avvia la code review.
4. **Code review:** valuta la mia implementazione rispetto alla traccia, spiega errori tecnici o concettuali e separa chiaramente problemi obbligatori da miglioramenti facoltativi. La code review è una fase di analisi del mio codice e non coincide con la scrittura della soluzione AI. Non modificare il mio codice, salvo richiesta esplicita.
5. **Correzioni:** se ci sono problemi obbligatori, lascia che li corregga e ripeti la code review finché l'implementazione soddisfa la traccia. In questa fase non creare ancora `aireview/`.
6. **Domande finali:** soltanto quando la code review non rileva più problemi obbligatori, formula domande basate sulla mia implementazione reale e sugli argomenti del blocco. Non dare per scontate classi, relazioni o scelte che non ho effettuato; eventuali casi diversi dal mio codice devono essere presentati esplicitamente come ipotesi. Valuta poi le mie risposte.
7. **Chiusura:** dopo che codice e risposte finali sono stati approvati, considera concluso l'esercizio, crea la tua implementazione separata dentro `aireview/` come termine di paragone e aggiorna `ROADMAP.md`.
