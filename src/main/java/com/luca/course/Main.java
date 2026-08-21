package com.luca.course;

public class Main {

    public static void main(String[] args) {

        Person person = new Customer("Pasquale", "Giglio", "C001");

        System.out.println(person.getFullName());

        Employee employee = new Employee(
                "Luca",
                "Bianchi",
                "Backend developer");

        System.out.println(person.getDescription());
        System.out.println(employee.getDescription());

        System.out.println("------------------------------");

        Person worker = new Employee(
                "Anna",
                "Verdi",
                "Project manager");

        System.out.println(worker.getDescription());

        System.out.println("------------------------------");

        NotificationChannel emailChannel = new EmailNotificationChannel();
        NotificationChannel smsChannel = new SmsNotificationChannel();

        sendOrderUpdate(emailChannel, "luca@example.com");
        sendOrderUpdate(smsChannel, "3331234567");

        System.out.println("------------------------------");

        EmailNotificationChannel email = new EmailNotificationChannel();

        NotificationChannel emailCh = email;
        ChannelHealthCheck emailHealthCheck = email;

        emailCh.send(
                "luca@example.com",
                "Il tuo ordine è stato spedito");

        System.out.println(
                "Email disponibile: " + emailHealthCheck.isAvailable());

        System.out.println("------------------------------");
        System.out.println("------------------------------");

        emailChannel.sendTestMessage("test@example.com");
        smsChannel.sendTestMessage("3330000000");

        System.out.println("------------------------------");
        System.out.println("------------------------------");
        System.out.println(email.getStatus());
    }

    static void sendOrderUpdate(NotificationChannel channel, String recipient) {
        channel.send(recipient, "Il tuo ordine è stato spedito");
    }
}
