# Esercizio 01 — Aggiornamento di una bozza d’ordine

## Scenario

Un’applicazione backend sta preparando una bozza d’ordine. Prima della conferma, un operatore può modificare quantità e prezzo. La bozza può anche avere una nota cliente assente.

Lo scopo non è progettare un sistema completo, ma verificare come valori primitivi e reference si comportano durante questi aggiornamenti.

## Consegna

Lavora nella variante `solution` e crea:

- `solution/Order.java`, che rappresenta la bozza;
- `solution/OrderUpdateExercise.java`, contenente il metodo `main` e i metodi necessari alla prova.

Usa questi package:

```text
com.luca.exercises.exercise01.orderupdate.solution
```

`Order` deve avere questi campi:

- nome del prodotto (`String`);
- quantità (`int`);
- prezzo unitario (`double`);
- nota del cliente (`String`, che può essere `null`).

Nel `main`:

1. Crea una bozza per due unità di un prodotto a tua scelta, senza nota cliente.
2. Crea con `var` una seconda variabile che punti alla stessa bozza, come se fosse la vista usata dall’operatore.
3. Modifica la quantità attraverso la seconda variabile e stampa la quantità leggendo dalla prima.
4. Scrivi un metodo che riceva la bozza e un nuovo prezzo, poi aggiorni il prezzo dell’oggetto.
5. Scrivi un secondo metodo che provi a sostituire il parametro con una nuova istanza di `Order`. Assegna alla nuova istanza un prodotto diverso, ma non restituirla.
6. Dopo entrambe le chiamate, stampa lo stato della variabile originale e osserva quali modifiche sono rimaste visibili.
7. Se la nota è `null`, stampa `Nessuna nota cliente`; altrimenti stampane il contenuto.
8. Calcola il totale usando quantità e prezzo unitario. Salvalo come `double`, poi crea anche una versione in euro interi tramite cast esplicito a `int`.
9. Stampa un riepilogo finale costruito tramite concatenazione di `String`.

Prima di eseguire il programma, annota come commento quale risultato ti aspetti dopo la modifica tramite alias e dopo il tentativo di sostituzione nel metodo.

Per la variante `aireview`, usa gli stessi nomi di classe nei package:

```text
com.luca.exercises.exercise01.orderupdate.aireview
```

## Vincoli

Non usare constructor espliciti, getter/setter, collections, record, ereditarietà o altri concetti non ancora affrontati.

## Domande finali

Quando hai terminato, prova a spiegare:

1. Perché la modifica fatta tramite la variabile dell’operatore è visibile dalla variabile originale?
2. Perché la riassegnazione del parametro nel secondo metodo non sostituisce la bozza originale?
3. Cosa succede alla parte decimale quando il totale viene convertito in `int`?
