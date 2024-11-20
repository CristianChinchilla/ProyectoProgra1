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
        ReserveSystem reserveSystem = new ReserveSystem();

        // Configuración del idioma
        System.out.println("Select language / Seleccione idioma: ");
        System.out.println("1. English");
        System.out.println("2. Español");
        int languageChoice = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Language systemLanguage = languageChoice == 2 ? new Language("ES", "Español") : new Language("EN", "English");
        reserveSystem.changeLanguage(systemLanguage);

        boolean running = true;

        // Menú principal
        while (running) {
            System.out.println(systemLanguage.getCode().equals("ES") ? "\n--- Menú Principal ---" : "\n--- Main Menu ---");
            System.out.println(systemLanguage.getCode().equals("ES")
                    ? "1. Registrar un espacio deportivo\n2. Listar espacios disponibles\n3. Realizar una reserva\n4. Cancelar una reserva\n5. Ver historial de reservas\n6. Guardar y salir"
                    : "1. Register a sports space\n2. List available spaces\n3. Make a reservation\n4. Cancel a reservation\n5. View reservation history\n6. Save and exit");
            System.out.print(systemLanguage.getCode().equals("ES") ? "Seleccione una opción: " : "Select an option: ");

            int option;
            try {
                option = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
            } catch (Exception e) {
                System.out.println(systemLanguage.getCode().equals("ES") ? "Opción inválida. Intente de nuevo." : "Invalid option. Please try again.");
                scanner.nextLine(); // Limpiar entrada
                continue;
            }

            switch (option) {
                case 1:
                    // Registrar un espacio deportivo
                    System.out.println(systemLanguage.getCode().equals("ES") ? "Ingrese el nombre del espacio deportivo: " : "Enter the name of the sports space: ");
                    String spaceName = scanner.nextLine();
                    System.out.println(systemLanguage.getCode().equals("ES") ? "Ingrese el tipo de espacio (Ejemplo: Fútbol, Baloncesto): " : "Enter the type of space (Example: Soccer, Basketball): ");
                    String spaceType = scanner.nextLine();
                    System.out.println(systemLanguage.getCode().equals("ES") ? "Ingrese la capacidad del espacio: " : "Enter the capacity of the space: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    SportsSpace newSpace = new SportsSpace(reserveSystem.generateSpaceId(), spaceName, spaceType, capacity);
                    reserveSystem.addSportsSpace(newSpace);
                    System.out.println(systemLanguage.getCode().equals("ES") ? "Espacio deportivo registrado con éxito." : "Sports space registered successfully.");
                    break;

                case 2:
                    // Listar espacios disponibles
                    System.out.println(systemLanguage.getCode().equals("ES")
                            ? "--- Espacios Disponibles ---"
                            : "--- Available Spaces ---");
                    reserveSystem.listSportsSpaces();
                    break;

                case 3:
                    // Realizar una reserva
                    System.out.println(systemLanguage.getCode().equals("ES") ? "Ingrese su nombre: " : "Enter your name: ");
                    String userName = scanner.nextLine();
                    System.out.println(systemLanguage.getCode().equals("ES") ? "Ingrese su correo electrónico: " : "Enter your email: ");
                    String userEmail = scanner.nextLine();

                    User user = new User(reserveSystem.generateUserId(), userName, userEmail, systemLanguage, NotificationType.APP);

                    System.out.println(systemLanguage.getCode().equals("ES") ? "Ingrese el ID del espacio deportivo a reservar: " : "Enter the ID of the sports space to reserve: ");
                    int spaceId = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    SportsSpace selectedSpace = reserveSystem.findSportsSpaceById(spaceId);
                    if (selectedSpace == null) {
                        System.out.println(systemLanguage.getCode().equals("ES") ? "Espacio deportivo no encontrado." : "Sports space not found.");
                        break;
                    }

                    System.out.println(systemLanguage.getCode().equals("ES") ? "Ingrese la fecha de la reserva (YYYY-MM-DD): " : "Enter the reservation date (YYYY-MM-DD): ");
                    String dateInput = scanner.nextLine();
                    LocalDate reservationDate = LocalDate.parse(dateInput);

                    System.out.println(systemLanguage.getCode().equals("ES") ? "Ingrese la hora de inicio (HH:MM): " : "Enter the start time (HH:MM): ");
                    String startTimeInput = scanner.nextLine();
                    LocalTime startTime = LocalTime.parse(startTimeInput);

                    System.out.println(systemLanguage.getCode().equals("ES") ? "Ingrese la hora de fin (HH:MM): " : "Enter the end time (HH:MM): ");
                    String endTimeInput = scanner.nextLine();
                    LocalTime endTime = LocalTime.parse(endTimeInput);

                    Reservation reservation = new Reservation(user, selectedSpace, reservationDate, startTime, endTime, 20.0);
                    if (reserveSystem.makeReservation(reservation)) {
                        System.out.println(systemLanguage.getCode().equals("ES") ? "Reserva realizada con éxito." : "Reservation made successfully.");
                    } else {
                        System.out.println(systemLanguage.getCode().equals("ES") ? "No se pudo realizar la reserva. Conflicto de horario." : "Could not make the reservation. Schedule conflict.");
                    }
                    break;

                case 4:
                    // Cancelar una reserva
                    System.out.println(systemLanguage.getCode().equals("ES") ? "Ingrese el ID de la reserva a cancelar: " : "Enter the ID of the reservation to cancel: ");
                    int reservationId = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    if (reserveSystem.cancelReservation(reservationId)) {
                        System.out.println(systemLanguage.getCode().equals("ES") ? "Reserva cancelada con éxito." : "Reservation cancelled successfully.");
                    } else {
                        System.out.println(systemLanguage.getCode().equals("ES") ? "Reserva no encontrada." : "Reservation not found.");
                    }
                    break;

                case 5:
                    // Ver historial de reservas
                    System.out.println(systemLanguage.getCode().equals("ES") ? "Historial de reservas:" : "Reservation history:");
                    reserveSystem.viewAllReservations();
                    break;

                case 6:
                    // Guardar y salir
                    reserveSystem.saveData();
                    System.out.println(systemLanguage.getCode().equals("ES") ? "Datos guardados. Saliendo del sistema..." : "Data saved. Exiting the system...");
                    running = false;
                    break;

                default:
                    System.out.println(systemLanguage.getCode().equals("ES") ? "Opción inválida. Intente de nuevo." : "Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
