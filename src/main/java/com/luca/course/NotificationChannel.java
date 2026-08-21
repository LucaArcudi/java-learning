package com.luca.course;

public interface NotificationChannel {

    void send(String recipient, String message);

    default void sendTestMessage(String recipient) {
        send(recipient, "Messaggio di test");
    }

    default String getStatus() {
        return "Canale configuarto";
    }
}