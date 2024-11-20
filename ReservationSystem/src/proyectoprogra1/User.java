package proyectoprogra1;

import java.util.ArrayList;
import java.util.List;


public class User {
    private int id;                           // ID único del usuario
    private String name;                      // Nombre del usuario
    private String email;                     // Correo electrónico del usuario
    private Language preferredLanguage;       // Idioma preferido del usuario
    private NotificationType preferredNotificationType; // Tipo de notificación preferido
    private List<Reservation> reservations;   // Historial de reservas del usuario

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

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    /**
     * Agrega una reserva al historial del usuario.
     *
     * @param reservation La reserva a agregar.
     */
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    /**
     * Cancela una reserva por su ID.
     *
     * @param reservationId ID de la reserva a cancelar.
     * @return true si la reserva fue cancelada, false si no se encontró.
     */
    public boolean cancelReservation(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                reservations.remove(reservation);
                return true;
            }
        }
        return false;
    }

    /**
     * Muestra el historial de reservas del usuario en el idioma seleccionado.
     */
    public void viewReservationHistory() {
        System.out.println(preferredLanguage.getCode().equals("ES") ?
                "Historial de reservas para " + name + ":" :
                "Reservation history for " + name + ":");

        if (reservations.isEmpty()) {
            System.out.println(preferredLanguage.getCode().equals("ES") ?
                    "No hay reservas registradas." :
                    "No reservations recorded.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation.getDetails(preferredLanguage));
            }
        }
    }

    /**
     * Devuelve los detalles del usuario.
     *
     * @return Una cadena con los detalles del usuario.
     */
    public String getDetails() {
        return preferredLanguage.getCode().equals("ES") ?
                "Usuario #" + id +
                "\nNombre: " + name +
                "\nCorreo Electrónico: " + email +
                "\nIdioma Preferido: " + preferredLanguage.getName() +
                "\nTipo de Notificación: " + preferredNotificationType :
                "User #" + id +
                "\nName: " + name +
                "\nEmail: " + email +
                "\nPreferred Language: " + preferredLanguage.getName() +
                "\nNotification Type: " + preferredNotificationType;
    }

    @Override
    public String toString() {
        return "User #" + id + " [" + name + "]";
    }
}