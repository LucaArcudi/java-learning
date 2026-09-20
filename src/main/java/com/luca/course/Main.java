package com.luca.course;

public class Main {

        public static void main(String[] args) {

                final Customer customer = new Customer("Luca", "Arcudi", "C001");

                customer.rename("Marco");

                System.out.println(customer.getFullName());

                // customer = new Customer("asd", "qwe", "c002");

                Address address = new Address("Roma", "Italia");

                Address newAddress = address.withCity("Milano");

                System.out.println(address.format());
                System.out.println(newAddress.format());

                System.out.println(newAddress == address);
                System.out.println("--------------------------------");

                Customer customer2 = new Customer("Luca", "Arcudi", "C001");

                CustomerCard customerCard2 = new CustomerCard(customer2);

                System.out.println(customerCard2.getCustomerName());

                customer2.rename("Marco");

                System.out.println(customerCard2.getCustomerName());

                System.out.println("--------------------------------");

                Dimensioni dim = new Dimensioni(210, 297);

                System.out.println(dim.getLarghezza());
                System.out.println(dim.getLunghezza());

                FormatoStampa fSt = new FormatoStampa(dim);

                fSt.getDimensioni().reSize(220, 312);

                System.out.println(fSt.getDimensioni().getLarghezza());
                System.out.println(fSt.getDimensioni().getLunghezza());
                System.out.println("--------------------------------");

                StatoCommessa stato1 = StatoCommessa.DA_AVVIARE;
                StatoCommessa stato2 = StatoCommessa.IN_STAMPA;

                System.out.println(stato1 == stato2);

                stato2 = StatoCommessa.DA_AVVIARE;

                System.out.println(stato1 == stato2);

                stato2 = StatoCommessa.DA_AVVIARE;

                System.out.println(stato2.getDescrizione());

                stato2 = StatoCommessa.IN_STAMPA;

                StatoCommessa stato3 = StatoCommessa.COMPLETATA;

                System.out.println(stato1.puoPassareA(StatoCommessa.IN_STAMPA));
                System.out.println(stato2.puoPassareA(StatoCommessa.COMPLETATA));
                System.out.println(stato2.puoPassareA(StatoCommessa.ANNULLATA));
                System.out.println(stato3.puoPassareA(StatoCommessa.IN_STAMPA));

                System.out.println("--------------------------------");

                System.out.println(address.city());
                System.out.println(address.country());

                System.out.println("--------------------------------");

                Address address1 = new Address("Roma", "Italia");
                Address address2 = new Address("Roma", "Italia");
                Address address3 = new Address("Milano", "Italia");

                System.out.println(address1 == address2);
                System.out.println(address1.equals(address2));
                System.out.println(address1.equals(address3));

                System.out.println("--------------------------------");

                Address address4 = new Address("  Roma  ", "  Italia  ");
                Address address5 = new Address("Roma", "Italia");
                Address address6 = new Address("Milano", "Italia");

                System.out.println(address1 == address2);
                System.out.println(address1.equals(address2));
                System.out.println(address1.equals(address3));

                System.out.println("--------------------------------");

                EsitoStampa esitoA = new StampaAvviata("S001");
                EsitoStampa esitoB = new StampaRifiutata("carta insufficiente");
                EsitoStampa esitoC = new StampaAvviata("S002");

                System.out.println(esitoA.descrizione());
                System.out.println(esitoB.descrizione());

                System.out.println("--------------------------------");

                mostraDettaglio(esitoA);
                mostraDettaglio(esitoB);
                mostraDettaglioOld(esitoA);
                mostraDettaglioOld(esitoB);

                System.out.println("--------------------------------");

                mostraSoloRifiuto(esitoA);
                mostraSoloRifiuto(esitoB);

                System.out.println("--------------------------------");

                mostraSoloAvvviataS001(esitoA);
                mostraSoloAvvviataS001(esitoC);

                System.out.println("--------------------------------");

                System.out.println(dettaglio(new StampaAvviata("S001")));
                System.out.println(dettaglio(new StampaRifiutata("Carta insufficiente")));

                System.out.println("--------------------------------");

                System.out.println(dettaglio2(new StampaAvviata("S001")));
                System.out.println(dettaglio2(new StampaAvviata("S002")));
                System.out.println(dettaglio2(new StampaRifiutata("Carta insufficiente")));
                System.out.println(dettaglio2(null));

                System.out.println("--------------------------------");

                System.out.println(dettaglio3(new StampaAvviata("S001")));
                System.out.println(dettaglio3(new StampaRifiutata("Carta insufficiente")));

        }

        static void sendOrderUpdate(NotificationChannel channel, String recipient) {
                channel.send(recipient, "Il tuo ordine è stato spedito");
        }

        static void mostraDettaglio(EsitoStampa esito) {
                if (esito instanceof StampaAvviata avviata) {
                        System.out.println(avviata.codiceStampante());
                } else if (esito instanceof StampaRifiutata rifiutata) {
                        System.out.println(rifiutata.motivo());
                }
        }

        static void mostraDettaglioOld(EsitoStampa esito) {
                if (esito instanceof StampaAvviata) {
                        StampaAvviata avviata = (StampaAvviata) esito;
                        System.out.println(avviata.codiceStampante());
                } else if (esito instanceof StampaRifiutata) {
                        StampaRifiutata rifiutata = (StampaRifiutata) esito;
                        System.out.println(rifiutata.motivo());
                }
        }

        static void mostraSoloRifiuto(EsitoStampa esito) {
                if (!(esito instanceof StampaRifiutata rifiutata)) {
                        return;
                }

                System.out.println(rifiutata.motivo());
        }

        // esempio pattern matching flow scoping
        static void mostraSoloAvvviataS001(EsitoStampa esito) {
                if (esito instanceof StampaAvviata avviata && avviata.codiceStampante().equals("S001")) {
                        System.out.println("Avviata sulla stampante S001");
                }
        }

        // esempio pattern matching con switch

        // Perché manca default? EsitoStampa è sealed e abbiamo coperto tutti i suoi
        // tipi ammessi. Se togli uno dei due casi, il compilatore segnala che lo switch
        // non copre tutti i casi.
        static String dettaglio(EsitoStampa esito) {
                return switch (esito) {
                case StampaAvviata avviata -> "Stampante: " + avviata.codiceStampante();
                case StampaRifiutata rifiutata -> "Motivo: " + rifiutata.motivo();
                };
        }

        // pattern matching: condizioni con when e gestione di null
        // when aggiunge una condizione: quel ramo viene scelto se il tipo corrisponde e
        // la condizione è vera. Altrimenti si prosegue con i casi successivi.
        static String dettaglio2(EsitoStampa esito) {
                return switch (esito) {
                case null -> "Esito assente";
                case StampaAvviata avviata when "S001"
                                .equals(avviata.codiceStampante()) -> "Avviata sulla stampante principale";
                case StampaAvviata avviata -> "Avviata sulla stampante " + avviata.codiceStampante();
                case StampaRifiutata rifiutata -> "Rifiutata: " + rifiutata.motivo();
                };
        }

        // pattern matching: record pattern
        // case StampaAvviata(String codice) Riconosce il record ed estrae direttamente
        // il suo componente nella variabile codice.
        // Con più componenti, conta l’ordine dichiarato nel record.
        static String dettaglio3(EsitoStampa esito) {
                return switch (esito) {
                case StampaAvviata(String codice) -> "Stampante: " + codice;
                case StampaRifiutata(String motivo) -> "Motivo: " + motivo;
                };
        }
}
