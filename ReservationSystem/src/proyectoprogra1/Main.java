
package proyectoprogra1;

/**
 * @author Cristian Gerardo Chichilla Fonseca
 * @author Eidan Alexandre Picado Leiva
 * @author Gerald Armando Maroto Fernandez
 * @author Jefferson Alexander Soto Ulate
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear sistema de reservas
        ReserveSystem reserveSystem = new ReserveSystem();

        // Configurar idioma del sistema
        System.out.println("Select language / Seleccione idioma: ");
        System.out.println("1. English");
        System.out.println("2. Español");
        int languageChoice = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Language systemLanguage = languageChoice == 2 ? new Language("ES", "Español") : new Language("EN", "English");
        reserveSystem.changeLanguage(systemLanguage);

        // Crear usuarios
        System.out.println(systemLanguage.getCode().equals("ES") ? "Ingrese el nombre del primer usuario: " : "Enter the first user's name: ");
        String user1Name = scanner.nextLine();
        System.out.println(systemLanguage.getCode().equals("ES") ? "Ingrese el correo del primer usuario: " : "Enter the first user's email: ");
        String user1Email = scanner.nextLine();

        User user1 = new User(1, user1Name, user1Email, systemLanguage, NotificationType.EMAIL);

        System.out.println(systemLanguage.getCode().equals("ES") ? "Ingrese el nombre del segundo usuario: " : "Enter the second user's name: ");
        String user2Name = scanner.nextLine();
        System.out.println(systemLanguage.getCode().equals("ES") ? "Ingrese el correo del segundo usuario: " : "Enter the second user's email: ");
        String user2Email = scanner.nextLine();

        User user2 = new User(2, user2Name, user2Email, systemLanguage, NotificationType.APP);

        // Crear espacio deportivo
        SportsSpace soccerField = new SportsSpace(
            1, 
            systemLanguage.getCode().equals("ES") ? "Cancha de Fútbol" : "Soccer Field", 
            "Fútbol", 
            20
        );

        // Agregar horarios disponibles
        soccerField.getAvailableSchedules().add(
        new Schedule(1, LocalDate.of(2024, 11, 20), LocalTime.of(9, 0), LocalTime.of(11, 0))
        );

        // Realizar reservas
        System.out.println(systemLanguage.getCode().equals("ES") ? "Intentando reservar para el primer usuario..." : "Attempting to reserve for the first user...");
        Reservation reservation1 = new Reservation(
            user1,
            soccerField,
            LocalDate.of(2024, 11, 20),
            LocalTime.of(9, 0),
            LocalTime.of(11, 0),
            20.0 // Precio
        );

        if (reserveSystem.makeReservation(reservation1)) {
            System.out.println(systemLanguage.getCode().equals("ES") ? "¡Reserva realizada con éxito!" : "Reservation completed successfully!");
        } else {
            System.out.println(systemLanguage.getCode().equals("ES") ? "No se pudo realizar la reserva." : "The reservation could not be completed.");
        }

        System.out.println(systemLanguage.getCode().equals("ES") ? "Intentando reservar para el segundo usuario..." : "Attempting to reserve for the second user...");
        Reservation reservation2 = new Reservation(
            user2,
            soccerField,
            LocalDate.of(2024, 11, 20),
            LocalTime.of(9, 0),
            LocalTime.of(11, 0),
            20.0 // Precio
        );

        if (reserveSystem.makeReservation(reservation2)) {
            System.out.println(systemLanguage.getCode().equals("ES") ? "¡Reserva realizada con éxito!" : "Reservation completed successfully!");
        } else {
            System.out.println(systemLanguage.getCode().equals("ES") ? "No se pudo realizar la reserva." : "The reservation could not be completed.");
        }

        // Historial de reservas
        System.out.println(systemLanguage.getCode().equals("ES") ? "\nHistorial de reservas del primer usuario:" : "\nReservation history for the first user:");
        user1.viewReservationHistory();

        System.out.println(systemLanguage.getCode().equals("ES") ? "\nHistorial de reservas del segundo usuario:" : "\nReservation history for the second user:");
        user2.viewReservationHistory();

        scanner.close();
    }
}