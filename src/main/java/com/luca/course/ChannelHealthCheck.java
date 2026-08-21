package com.luca.course;

public interface ChannelHealthCheck {
    boolean isAvailable();

    default String getStatus() {
        if (isAvailable()) {

            return "Canale disponibile";
        } else {
            return "Canale non disponibile";
        }

    }
}
