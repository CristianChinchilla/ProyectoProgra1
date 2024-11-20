package proyectoprogra1;

import java.util.ArrayList;
import java.util.List;
import proyectoprogra1.Reservation;
import proyectoprogra1.SportsSpace;
import proyectoprogra1.User;
import proyectoprogra1.Language;

public class ReserveSystem {
    private List<Reservation> reservations; // Lista de reservas
    private List<SportsSpace> sportsSpaces; // Lista de espacios deportivos
    private List<User> users;               // Lista de usuarios registrados
    private Language currentLanguage;       // Idioma actual del sistema

    public ReserveSystem() {
        this.reservations = new ArrayList<>();
        this.sportsSpaces = new ArrayList<>();
        this.users = new ArrayList<>();
        this.currentLanguage = new Language("EN", "English"); // Idioma predeterminado
    }

    // Cambiar el idioma del sistema
    public void changeLanguage(Language language) {
        this.currentLanguage = language;
        System.out.println(language.getCode().equals("ES") ? "Idioma cambiado a Español" : "Language changed to English");
    }

    // Registrar un nuevo usuario
    public void registerUser(User user) {
        users.add(user);
        System.out.println(currentLanguage.getCode().equals("ES") ?
                "Usuario registrado exitosamente: " + user.getName() :
                "User registered successfully: " + user.getName());
    }

    // Agregar un espacio deportivo
    public void addSportsSpace(SportsSpace space) {
        sportsSpaces.add(space);
        System.out.println(currentLanguage.getCode().equals("ES") ?
                "Espacio deportivo agregado: " + space.getName() :
                "Sports space added: " + space.getName());
    }

    // Reservar un espacio deportivo
    public boolean makeReservation(Reservation reservation) {
        if (reservation.getSportsSpace().isAvailable(
                reservation.getDate(),
                reservation.getStartTime(),
                reservation.getEndTime())) {
            reservations.add(reservation);
            reservation.getSportsSpace().markAsReserved(reservation);
            reservation.getUser().addReservation(reservation);
            System.out.println(currentLanguage.getCode().equals("ES") ?
                    "Reserva realizada exitosamente para " + reservation.getUser().getName() :
                    "Reservation successfully made for " + reservation.getUser().getName());
            return true;
        }
        System.out.println(currentLanguage.getCode().equals("ES") ?
                "No se pudo realizar la reserva. El espacio no está disponible." :
                "Reservation failed. The space is not available.");
        return false;
    }

    // Cancelar una reserva
    public boolean cancelReservation(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                reservations.remove(reservation);
                reservation.getSportsSpace().unmarkReservation(reservation);
                reservation.getUser().cancelReservation(reservationId);
                System.out.println(currentLanguage.getCode().equals("ES") ?
                        "Reserva cancelada exitosamente: ID " + reservationId :
                        "Reservation successfully cancelled: ID " + reservationId);
                return true;
            }
        }
        System.out.println(currentLanguage.getCode().equals("ES") ?
                "No se encontró la reserva con ID: " + reservationId :
                "Reservation with ID " + reservationId + " not found.");
        return false;
    }

    // Ver historial de reservas del sistema
    public void viewSystemReservationHistory() {
        System.out.println(currentLanguage.getCode().equals("ES") ?
                "Historial de todas las reservas del sistema:" :
                "System-wide reservation history:");
        if (reservations.isEmpty()) {
            System.out.println(currentLanguage.getCode().equals("ES") ?
                    "No hay reservas registradas." :
                    "No reservations recorded.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation.getDetails(currentLanguage));
            }
        }
    }

    // Listar espacios deportivos disponibles
    public void listAvailableSportsSpaces() {
        System.out.println(currentLanguage.getCode().equals("ES") ?
                "Espacios deportivos disponibles:" :
                "Available sports spaces:");
        for (SportsSpace space : sportsSpaces) {
            System.out.println("- " + space.getName());
        }
    }

    // Listar usuarios registrados
    public void listRegisteredUsers() {
        System.out.println(currentLanguage.getCode().equals("ES") ?
                "Usuarios registrados:" :
                "Registered users:");
        for (User user : users) {
            System.out.println("- " + user.getName());
        }
    }
}