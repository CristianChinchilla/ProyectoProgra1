package proyectoprogra1;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReserveSystem {

    private List<Reservation> reservations; // Lista de reservas
    private List<SportsSpace> sportsSpaces; // Lista de espacios deportivos
    private int nextSpaceId;                // Contador para IDs de espacios deportivos
    private int nextUserId;                 // Contador para IDs de usuarios
    private List<User> users;               // Lista de usuarios registrados
    private Language currentLanguage;       // Idioma actual del sistema

    public ReserveSystem() {
        this.reservations = new ArrayList<>();
        this.sportsSpaces = new ArrayList<>();
        this.users = new ArrayList<>();
        this.currentLanguage = new Language("EN", "English"); // Idioma predeterminado
    }

    // Método para generar un ID único para los espacios deportivos
    public int generateSpaceId() {
        return nextSpaceId++;
    }

    // Método para generar un ID único para los usuarios
    public int generateUserId() {
        return nextUserId++;
    }

    // Cambiar el idioma del sistema
    public void changeLanguage(Language language) {
        this.currentLanguage = language;
        System.out.println(language.getCode().equals("ES") ? "Idioma cambiado a Español" : "Language changed to English");
    }

    // Registrar un nuevo usuario
    public void registerUser(User user) {
        users.add(user);
        System.out.println(currentLanguage.getCode().equals("ES")
                ? "Usuario registrado exitosamente: " + user.getName()
                : "User registered successfully: " + user.getName());
    }

    // Agregar un espacio deportivo
    // Agregar un espacio deportivo
    public void addSportsSpace(SportsSpace space) {
        // Asignar ID de manera automática
        space.setId(sportsSpaces.size() + 1); // El ID empieza desde 1
        sportsSpaces.add(space);

        // Imprimir el ID del nuevo espacio
        System.out.println(currentLanguage.getCode().equals("ES")
                ? "Espacio deportivo registrado con éxito. ID: " + space.getId()
                : "Sports space successfully registered. ID: " + space.getId());
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
            System.out.println(currentLanguage.getCode().equals("ES")
                    ? "Reserva realizada exitosamente para " + reservation.getUser().getName()
                    : "Reservation successfully made for " + reservation.getUser().getName());
            return true;
        }
        System.out.println(currentLanguage.getCode().equals("ES")
                ? "No se pudo realizar la reserva. El espacio no está disponible."
                : "Reservation failed. The space is not available.");
        return false;
    }
    /**
     * Guarda toda la información acerca de los espacios deportivos y las reservaciones
     */
    public void saveData() {
        try {
            // Guardar espacios deportivos
            BufferedWriter spaceWriter = new BufferedWriter(new FileWriter("sportsSpaces.txt"));
            for (SportsSpace space : sportsSpaces) {
                spaceWriter.write(space.getId() + "," + space.getName() + "," + space.getType() + "," + space.getCapacity());
                spaceWriter.newLine();
            }
            spaceWriter.close();

            // Guardar reservas
            BufferedWriter reservationWriter = new BufferedWriter(new FileWriter("reservations.txt"));
            for (Reservation reservation : reservations) {
                reservationWriter.write(reservation.getId() + "," + reservation.getUser().getId() + ","
                        + reservation.getSportsSpace().getId() + "," + reservation.getDate() + ","
                        + reservation.getStartTime() + "," + reservation.getEndTime() + ","
                        + reservation.getPrice() + "," + reservation.getStatus());
                reservationWriter.newLine();
            }
            reservationWriter.close();

            System.out.println(currentLanguage.getCode().equals("ES")
                    ? "Datos guardados correctamente."
                    : "Data saved successfully.");
        } catch (IOException e) {
            System.out.println(currentLanguage.getCode().equals("ES")
                    ? "Error al guardar los datos."
                    : "Error saving data.");
            e.printStackTrace();
        }
    }

    /**
     * Cancela una reservacion ya existente
     * @param reservationId
     * @return 
     */
    public boolean cancelReservation(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                reservations.remove(reservation);
                reservation.getSportsSpace().unmarkReservation(reservation);
                reservation.getUser().cancelReservation(reservationId);
                System.out.println(currentLanguage.getCode().equals("ES")
                        ? "Reserva cancelada exitosamente: ID " + reservationId
                        : "Reservation successfully cancelled: ID " + reservationId);
                return true;
            }
        }
        System.out.println(currentLanguage.getCode().equals("ES")
                ? "No se encontró la reserva con ID: " + reservationId
                : "Reservation with ID " + reservationId + " not found.");
        return false;
    }
    /**
     * Muestra una lista con todas las reservaciones creadas
     */
    public void viewAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println(currentLanguage.getCode().equals("ES")
                    ? "No hay reservas realizadas."
                    : "No reservations made.");
        } else {
            System.out.println(currentLanguage.getCode().equals("ES")
                    ? "--- Listado de Reservas ---"
                    : "--- Reservation List ---");

            for (Reservation reservation : reservations) {
                System.out.println(reservation.getDetails(currentLanguage));
                System.out.println("-------------------------");
            }
        }
    }
    /**
     * Mustras una lista con todos los espacios deportivos guardados
     */
    public void listSportsSpaces() {
        if (sportsSpaces.isEmpty()) {
            System.out.println(currentLanguage.getCode().equals("ES")
                    ? "No hay espacios deportivos disponibles."
                    : "No sports spaces available.");
        } else {
            System.out.println(currentLanguage.getCode().equals("ES")
                    ? "--- Espacios Deportivos Disponibles ---"
                    : "--- Available Sports Spaces ---");

            for (SportsSpace space : sportsSpaces) {
                System.out.println(space.getDetails(currentLanguage));
            }
        }
    }

    /**
     * Muestra un historia con todas las resevar hechas anteriormente
     */
    public void viewSystemReservationHistory() {
        System.out.println(currentLanguage.getCode().equals("ES")
                ? "Historial de todas las reservas del sistema:"
                : "System-wide reservation history:");
        if (reservations.isEmpty()) {
            System.out.println(currentLanguage.getCode().equals("ES")
                    ? "No hay reservas registradas."
                    : "No reservations recorded.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation.getDetails(currentLanguage));
            }
        }
    }
    /**
     * Busca un espacio deportivo mediante el ID de este
     * @param id
     * @return 
     */
    public SportsSpace findSportsSpaceById(int id) {
        for (SportsSpace space : sportsSpaces) {
            if (space.getId() == id) {
                return space; // Espacio deportivo encontrado
            }
        }
        return null; // No se encontró el espacio deportivo con el ID dado
    }
    /**
     * Muestra una lista con todos los espacios disponibles
     */
    public void listAvailableSportsSpaces() {
        System.out.println(currentLanguage.getCode().equals("ES")
                ? "Espacios deportivos disponibles:"
                : "Available sports spaces:");
        for (SportsSpace space : sportsSpaces) {
            System.out.println("- " + space.getName());
        }
    }
    /**
     * Muestra una lista con todos los usuarios registrados en el sistema
     */
    public void listRegisteredUsers() {
        System.out.println(currentLanguage.getCode().equals("ES")
                ? "Usuarios registrados:"
                : "Registered users:");
        for (User user : users) {
            System.out.println("- " + user.getName());
        }
    }

}
