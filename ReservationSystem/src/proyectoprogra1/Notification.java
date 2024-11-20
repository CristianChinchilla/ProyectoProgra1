package proyectoprogra1;

import java.time.LocalDateTime;

public class Notification {
    private int id;                      // ID único de la notificación
    private String message;              // Mensaje de la notificación
    private LocalDateTime dateTime;      // Fecha y hora de la notificación
    private NotificationType type;       // Tipo de notificación (EMAIL, APP, SMS, etc.)
    private boolean isSent;              // Indica si la notificación ha sido enviada

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

    // Métodos clave

    /**
     * Envía la notificación si no ha sido enviada previamente.
     * @param language Idioma en el que se generará el mensaje de confirmación.
     */
    public void send(Language language) {
        if (!isSent) {
            System.out.println(language.getCode().equals("ES") ?
                    "Enviando notificación (" + type + "): " + message :
                    "Sending notification (" + type + "): " + message);
            isSent = true;
        } else {
            System.out.println(language.getCode().equals("ES") ?
                    "La notificación ya fue enviada." :
                    "Notification has already been sent.");
        }
    }

    /**
     * Devuelve los detalles de la notificación.
     * @param language Idioma en el que se generará el mensaje.
     * @return Cadena con los detalles de la notificación.
     */
    public String getDetails(Language language) {
        return language.getCode().equals("ES") ?
                "Notificación #" + id +
                "\nMensaje: " + message +
                "\nFecha y Hora: " + dateTime +
                "\nTipo: " + type +
                "\nEstado: " + (isSent ? "Enviada" : "Pendiente") :
                "Notification #" + id +
                "\nMessage: " + message +
                "\nDateTime: " + dateTime +
                "\nType: " + type +
                "\nStatus: " + (isSent ? "Sent" : "Pending");
    }

    @Override
    public String toString() {
        return "Notification #" + id + " [" + type + "] - " + (isSent ? "Sent" : "Pending");
    }
}