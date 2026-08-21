package com.luca.course;

public class EmailNotificationChannel implements NotificationChannel, ChannelHealthCheck {

    @Override
    public void send(String recipient, String message) {
        System.out.println(recipient + " - " + message);
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public String getStatus() {
        return NotificationChannel.super.getStatus()
                + " - "
                + ChannelHealthCheck.super.getStatus();
    }
}
