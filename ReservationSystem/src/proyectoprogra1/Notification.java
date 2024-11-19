package proyectoprogra1;

import java.time.LocalDateTime;

public class Notification {
    private int id;                      // ID único de la notificación
    private String message;              // Mensaje de la notificación
    private LocalDateTime dateTime;      // Fecha y hora de la notificación
    private NotificationType type;       // Tipo de notificación (Email, App, etc.)
    private boolean isSent;              // Indica si la notificación fue enviada

    // Constructor
    public Notification(int id, String message, LocalDateTime dateTime, NotificationType type) {
        this.id = id;
        this.message = message;
        this.dateTime = dateTime;
        this.type = type;
        this.isSent = false; // Por defecto, la notificación no está enviada
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public boolean isSent() {
        return isSent;
    }

    public void markAsSent() {
        this.isSent = true;
    }

    // Métodos clave
    public void send() {
        if (!isSent) {
            System.out.println("Sending " + type + " notification: " + message);
            markAsSent(); // Marca como enviada
        } else {
            System.out.println("Notification already sent.");
        }
    }

    @Override
    public String toString() {
        return "Notification ID: " + id +
               "\nMessage: " + message +
               "\nDateTime: " + dateTime +
               "\nType: " + type +
               "\nStatus: " + (isSent ? "Sent" : "Pending");
    }
}
