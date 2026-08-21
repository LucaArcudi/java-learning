package com.luca.course;

public class SmsNotificationChannel implements NotificationChannel {

    @Override
    public void send(String recipient, String message) {
        System.out.println(recipient + " - " + message);
    }
}
