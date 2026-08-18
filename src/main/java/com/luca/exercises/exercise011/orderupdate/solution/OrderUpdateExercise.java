package com.luca.exercises.exercise011.orderupdate.solution;

public class OrderUpdateExercise {
    public static void main(String[] args) {
        // Crea una bozza per due unità di un prodotto a tua scelta, senza nota cliente.
        Order draftOrder = new Order();
        draftOrder.productName = "Laptop";
        draftOrder.quantity = 2;
        draftOrder.unitPrice = 1200.50;

        // Crea con `var` una seconda variabile che punti alla stessa bozza, come se
        // fosse la vista usata dall’operatore.
        var updatedOrder = draftOrder;

        // Modifica la quantità attraverso la seconda variabile e stampa la quantità
        // leggendo dalla prima.
        updatedOrder.quantity = 3;
        System.out.println("Updated quantity: " + draftOrder.quantity); // Updated quantity: 3

        // Aggiorna il prezzo della bozza usando il metodo `updateUnitPrice`.
        updateUnitPrice(draftOrder, 1099.99);

        // Dopo entrambe le chiamate, stampa lo stato della variabile originale e
        // osserva quali modifiche sono rimaste visibili.
        System.out.println("----------");
        replaceOrder(draftOrder);
        System.out.println("Product: " + draftOrder.productName); // Product: Laptop
        System.out.println("Quantity: " + draftOrder.quantity); // Quantity: 3
        System.out.println("Unit Price: " + draftOrder.unitPrice); // Unit Price: 1099.99
        System.out.println("Client Note: " + draftOrder.clientNote); // Client Note: null

        // Se la nota è `null`, stampa `Nessuna nota cliente`; altrimenti stampane il
        // contenuto.
        System.out.println("----------");
        if (draftOrder.clientNote == null) {
            System.out.println("No client note");
        } else {
            System.out.println("Client Note: " + draftOrder.clientNote);
        }

        // Calcola il totale usando quantità e prezzo unitario. Salvalo come
        // `double`.
        System.out.println("----------");
        double totalPrice = draftOrder.quantity * draftOrder.unitPrice;
        System.out.println("Total Price: €" + totalPrice); // Total Price: 3299.97

        // Crea anche una versione in euro interi tramite cast esplicito a
        // `int`.
        System.out.println("----------");
        int totalPriceInt = (int) totalPrice;
        System.out.println("Total Price (int): €" + totalPriceInt + ",00"); // Total Price (int): 3299

        // Stampa un riepilogo finale costruito tramite concatenazione di `String`.
        System.out.println("----------");
        String report = "Product: " + draftOrder.productName + "\n" +
                "Quantity: " + draftOrder.quantity + "\n" +
                "Unit Price: €" + draftOrder.unitPrice + "\n" +
                "Total Price: €" + totalPriceInt + ",00\n" +
                (draftOrder.clientNote == null ? "No client note" : "Client Note: " + draftOrder.clientNote);

        System.out.println(report);
    }

    // Scrivi un metodo che riceva la bozza e un nuovo prezzo, poi aggiorni il
    // prezzo dell’oggetto.
    public static void updateUnitPrice(Order draftOrder, double newPrice) {
        draftOrder.unitPrice = newPrice;
    }

    // Scrivi un secondo metodo che provi a sostituire il parametro con una nuova
    // istanza di `Order`. Assegna alla nuova istanza un prodotto diverso, ma non
    // restituirla.
    public static void replaceOrder(Order draftOrder) {
        draftOrder = new Order();
        draftOrder.productName = "Smartphone";
        draftOrder.quantity = 1;
        draftOrder.unitPrice = 800.00;
    }
}
