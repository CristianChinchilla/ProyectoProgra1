package proyectoprogra1;

import java.util.ArrayList;
import java.util.List;


public class User {
    private int id;                     // ID único del usuario
    private String name;                // Nombre del usuario
    private String email;               // Correo electrónico del usuario
    private Language preferredLanguage; // Idioma preferido del usuario
    private NotificationType preferredNotificationType; // Tipo de notificación preferido
    private List<Reservation> reservations; // Lista de reservas del usuario

    // Constructor
    public User(int id, String name, String email, Language preferredLanguage, NotificationType preferredNotificationType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.preferredLanguage = preferredLanguage;
        this.preferredNotificationType = preferredNotificationType;
        this.reservations = new ArrayList<>();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Language getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(Language preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public NotificationType getPreferredNotificationType() {
        return preferredNotificationType;
    }
    
    public void setPreferredNotificationType(NotificationType preferredNotificationType) {
        this.preferredNotificationType = preferredNotificationType;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    // Métodos clave
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void cancelReservation(int reservationId) {
        reservations.removeIf(reservation -> reservation.getId() == reservationId);
    }

    public void viewReservationHistory() {
        System.out.println("Reservation History for " + name + ":");
        for (Reservation reservation : reservations) {
            System.out.println(reservation.getDetails());
        }
    }
}
