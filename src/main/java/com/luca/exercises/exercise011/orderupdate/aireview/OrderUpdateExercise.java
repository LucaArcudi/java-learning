package com.luca.exercises.exercise011.orderupdate.aireview;

public class OrderUpdateExercise {
    public static void main(String[] args) {
        Order draftOrder = new Order();
        draftOrder.productName = "Monitor";
        draftOrder.quantity = 2;
        draftOrder.unitPrice = 249.50;
        draftOrder.clientNote = null;

        var operatorView = draftOrder;

        // Previsione: draftOrder.quantity varrà 3 perché le due variabili
        // contengono una reference allo stesso oggetto.
        operatorView.quantity = 3;
        System.out.println("Quantità letta dalla bozza originale: " + draftOrder.quantity);

        updateUnitPrice(draftOrder, 219.75);

        // Previsione: il prodotto della bozza originale resterà "Monitor".
        // Il metodo riassegna soltanto la propria copia della reference.
        replaceOrder(draftOrder);

        System.out.println("Prodotto dopo il tentativo di sostituzione: " + draftOrder.productName);
        System.out.println("Quantità: " + draftOrder.quantity);
        System.out.println("Prezzo unitario: €" + draftOrder.unitPrice);

        String noteSummary;
        if (draftOrder.clientNote == null) {
            noteSummary = "Nessuna nota cliente";
        } else {
            noteSummary = "Nota cliente: " + draftOrder.clientNote;
        }
        System.out.println(noteSummary);

        double total = draftOrder.quantity * draftOrder.unitPrice;
        int totalInWholeEuros = (int) total;

        System.out.println("Totale: €" + total);
        System.out.println("Totale in euro interi: €" + totalInWholeEuros);

        String finalSummary = "Prodotto: " + draftOrder.productName
                + "\nQuantità: " + draftOrder.quantity
                + "\nPrezzo unitario: €" + draftOrder.unitPrice
                + "\nTotale: €" + total
                + "\nTotale in euro interi: €" + totalInWholeEuros
                + "\n" + noteSummary;

        System.out.println("----------");
        System.out.println(finalSummary);
    }

    public static void updateUnitPrice(Order order, double newUnitPrice) {
        order.unitPrice = newUnitPrice;
    }

    public static void replaceOrder(Order order) {
        order = new Order();
        order.productName = "Stampante";
    }

}
