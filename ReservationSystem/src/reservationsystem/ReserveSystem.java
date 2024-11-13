package reservationsystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Eidan Alexandre Picado Leiva
 * 
 */
class ReserveSystem {

    private File file = new File("Reservations.txt");
    private List<Reservation> reservations = new ArrayList<>();
    private Language language;

    public ReserveSystem() {
        loadReservations(); // Cargar las reservas desde el archivo al iniciar el sistema
    }

    // Método para guardar todas las reservas en el archivo
    public void saveReservations() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(reservations);
        } catch (IOException e) {
            System.out.println("Error al guardar las reservas: " + e.getMessage());
        }
    }

    // Método para cargar reservas desde el archivo
    public void loadReservations() {
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                reservations = (List<Reservation>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar las reservas: " + e.getMessage());
            }
        }
    }

    // Método para registrar un usuario (aquí sería una implementación básica)
    public void register() {
        System.out.println("Función de registro pendiente de implementación.");
        // Implementar lógica de registro de usuario
    }

    // Método para iniciar sesión
    public void login() {
        System.out.println("Función de inicio de sesión pendiente de implementación.");
        // Implementar lógica de inicio de sesión de usuario
    }

    // Método para cerrar sesión
    public void logout() {
        System.out.println("Sesión cerrada.");
    }

    // Método para elegir el idioma del sistema
    public void chooseLanguage(Language language) {
        this.language = language;
        System.out.println("Idioma seleccionado: " + language);
    }

    // Método para ver los espacios deportivos disponibles
    public List<SportsSpace> viewAvailableSpaces() {
        List<SportsSpace> availableSpaces = new ArrayList<>();
        // Agregar lógica para filtrar y devolver espacios deportivos disponibles
        System.out.println("Mostrando espacios deportivos disponibles...");
        return availableSpaces;
    }

    // Método para hacer una reserva
    public Reservation makeReservation(Reservation reservation) {
        for (Reservation existingReservation : reservations) {
            if (existingReservation.conflictsWith(reservation)) {
                System.out.println("Conflicto de horarios. La reserva no se puede realizar.");
                return null; // No se realiza la reserva debido a conflicto
            }
        }
        reservations.add(reservation);
        saveReservations(); // Guardar cambios en el archivo
        System.out.println("Reserva realizada con éxito.");
        return reservation;
    }

    // Método para cancelar una reserva por su ID
    public boolean cancelReservation(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                reservations.remove(reservation);
                saveReservations(); // Guardar cambios en el archivo
                System.out.println("Reserva cancelada con éxito.");
                return true;
            }
        }
        System.out.println("Reserva no encontrada.");
        return false;
    }

    // Método para ver el historial de reservas
    public List<Reservation> viewReservationHistory() {
        System.out.println("Mostrando historial de reservas...");
        return reservations;
    }

    // Método para simular la compartición en redes sociales
    public void shareReservationOnSocialMedia(Reservation reservation) {
        System.out.println("Compartiendo la reserva en redes sociales...");
        // Implementar integración real con redes sociales aquí
    }
}
