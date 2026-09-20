# Java Learning

## Obiettivo

Questo repository serve per imparare Java moderno partendo da un background PHP/Yii2/Yii3/Laravel.

Printshop è il progetto da portare avanti lungo tutto Java Core e, quando il percorso arriverà a Spring, anche con Spring. Usa questo dominio come base per gli esercizi e le evoluzioni successive, introducendo funzionalità e cambiamenti pertinenti agli argomenti via via studiati. Non considerarlo un esercizio usa e getta e non anticipare ora strumenti o architetture delle tappe future. Rimangono valide l'autonomia della mia implementazione e la separazione dalla versione AI.

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
- Negli esercizi progetto e implemento in modo indipendente. Non richiedere una proposta progettuale in chat, una progettazione condivisa o la tua approvazione prima che io inizi a scrivere codice. Durante lo svolgimento intervieni soltanto su mia richiesta esplicita; alla consegna segui il flusso previsto sotto.
- Ogni traccia deve includere un riepilogo separato degli strumenti tecnici già studiati nel blocco e utilizzabili per svolgere l'esercizio, basandosi su `JAVA_PROGRESS.md`.
- Quando chiedo chiarimenti su una traccia o su un requisito, spiegali esclusivamente in linguaggio funzionale e di dominio. Non nominare né suggerire costrutti Java, firme, classi, metodi, pattern o scelte progettuali e non rivelare quale argomento del corso viene verificato, salvo mia richiesta esplicita.
- Nella valutazione degli esercizi concentrati sulla correttezza della logica e del comportamento. Testo, formattazione e formulazione delle stringhe non sono requisiti bloccanti, salvo che la traccia dichiari esplicitamente un output esatto come funzionalmente rilevante. Non richiedere modifiche puramente cosmetiche per dimostrare un comportamento già verificabile dal codice o dall'esecuzione.
- Nell'implementazione AI dentro `aireview/`, evita di anticipare argomenti non ancora affrontati.
- Usa Java 25 e Maven.
- Spring Boot verrà affrontato più avanti: per ora Java Core.
- Aggiorna la roadmap ad ogni punto smarcato.

## Flusso degli esercizi conclusivi

1. **Preparazione:** crea soltanto `trace.md`, con scenario, requisiti funzionali, obiettivi tecnici, vincoli, criteri di superamento e riepilogo degli strumenti già studiati. Non anticipare la soluzione.
2. **Svolgimento:** progetto la struttura e scrivo il codice autonomamente, creando la cartella `solution/`, senza passaggi obbligatori di confronto o approvazione preventiva con l'AI.
3. **Consegna:** quando dichiaro finito l'esercizio, ispeziona il mio codice e verifica compilazione e comportamento. Puoi verificare i casi direttamente sui metodi senza obbligarmi a completarli nel `main`. Segnala sinteticamente eventuali problemi reali, senza modificare la mia soluzione né imporre un ciclo di correzioni.
4. **Versione di confronto:** alla consegna implementa direttamente la tua soluzione separata dentro `aireview/`, senza aspettare un'altra richiesta. Usa soltanto le conoscenze Java già acquisite e verifica compilazione ed esecuzione. Mantieni intatti il mio codice, la mia progettazione e gli eventuali documenti già presenti. Non generare documenti di valutazione o passaggi obbligatori aggiuntivi.
5. **Confronto e chiusura:** sarò io a chiedere chiarimenti o approfondimenti confrontando le due implementazioni. Il confronto e le eventuali correzioni sono facoltativi, non un requisito per proseguire. Alla chiusura aggiorna `JAVA_PROGRESS.md` e, se il relativo punto è interamente completato, `ROADMAP.md`, riportando con trasparenza eventuali limiti emersi dalla verifica.
