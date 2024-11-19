package proyectoprogra1;

import java.util.ArrayList;
import java.util.List;

public class ReserveSystem {
    private List<Reservation> reservations; // Lista de reservas
    private List<SportsSpace> sportsSpaces; // Lista de espacios deportivos
    private Language currentLanguage;       // Idioma actual del sistema

    public ReserveSystem() {
        this.reservations = new ArrayList<>();
        this.sportsSpaces = new ArrayList<>();
        this.currentLanguage = new Language("EN", "English"); // Idioma predeterminado
    }

    // Registrar un nuevo usuario en el sistema
    public void registerUser(User user) {
        System.out.println("User " + user.getName() + " registered successfully.");
    }

    // Cambiar el idioma del sistema
    public void changeLanguage(Language language) {
        this.currentLanguage = language;
        System.out.println("System language changed to: " + language.getName());
    }

    // Agregar un espacio deportivo
    public void addSportsSpace(SportsSpace space) {
        sportsSpaces.add(space);
        System.out.println("Sports space added: " + space.getName());
    }

    // Reservar un espacio deportivo
    public boolean makeReservation(Reservation reservation) {
        if (reservation.getSportsSpace().isAvailable(
                reservation.getDate(),
                reservation.getStartTime(),
                reservation.getEndTime())) {
            reservations.add(reservation);
            reservation.getSportsSpace().markAsReserved(reservation);
            System.out.println("Reservation made successfully.");
            return true;
        }
        System.out.println("Reservation failed: Space is not available.");
        return false;
    }

    // Cancelar una reserva
    public boolean cancelReservation(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                reservations.remove(reservation);
                reservation.getSportsSpace().unmarkReservation(reservation);
                System.out.println("Reservation canceled successfully.");
                return true;
            }
        }
        System.out.println("Reservation ID not found.");
        return false;
    }

    // Ver historial de reservas
    public void viewReservationHistory() {
        System.out.println("Reservation History:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation.getDetails());
        }
    }

    // Compartir una reserva en redes sociales (simulado)
    public void shareReservationOnSocialMedia(Reservation reservation) {
        System.out.println("Sharing reservation: " + reservation.getDetails());
        // Aquí se integraría con una API para compartir
    }
}
