# Risposte finali

## 1. Perché la modifica fatta tramite la variabile dell’operatore è visibile dalla variabile originale?

Perchè il valore che viene assegnato alla nuova variabile, in questo caso rappresenta la referenza* della variabile, questo vale per gli oggetti, e in generale, per le variabili reference type, a differenza delle variabili primitive, dove il valore è quello rappresentato. 

EDIT

reference dell'oggetto contenuto nella variabile

## 2. Perché la riassegnazione del parametro nel secondo metodo non sostituisce la bozza originale?

Perchè viene creata una nuova istanza dentro il metodo con una sua referenza, diversa da quella passata.*

EDIT

Al parametro locale viene assegnata una nuova reference e quella originale rimane intatta

## 3. Cosa succede alla parte decimale quando il totale viene convertito in `int`?

L'informazione viene persa e non sarà possibile recuperarla.