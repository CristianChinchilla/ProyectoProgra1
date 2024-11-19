package reservationsystem;

import java.util.Scanner;
import java.util.List;

/**
 *
 * @author tu_nombre
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ReserveSystem reserveSystem = new ReserveSystem();
        User currentUser = null;

        // Cargar datos persistentes
        reserveSystem.loadReservations();
        reserveSystem.loadSportsSpaces();

        System.out.println("Bienvenido al Sistema de Reservas de Espacios Deportivos");

        // Elegir idioma (Soporte multilingüe)
        System.out.println("Seleccione su idioma / Select your language:");
        System.out.println("1. Español");
        System.out.println("2. English");
        int languageChoice = Integer.parseInt(scanner.nextLine());

        Language language;
        if (languageChoice == 1) {
            language = new Language("es", "Español");
        } else {
            language = new Language("en", "English");
        }
        reserveSystem.chooseLanguage(language);

        boolean exit = false;
        while (!exit) {
            // Mostrar menú principal
            System.out.println("\nPor favor, seleccione una opción:");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> // Registro de usuario
                    currentUser = register(scanner, reserveSystem);
                case "2" -> // Inicio de sesión
                    currentUser = login(scanner, reserveSystem);
                case "3" -> {
                    // Salir del sistema
                    exit = true;
                    System.out.println("Gracias por usar el sistema. ¡Hasta pronto!");
                }
                default -> System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }

            // Si el usuario ha iniciado sesión, mostrar menú de opciones
            if (currentUser != null) {
                boolean loggedIn = true;
                while (loggedIn) {
                    System.out.println("\nBienvenido, " + currentUser.getName());
                    System.out.println("Por favor, seleccione una opción:");
                    System.out.println("1. Ver espacios deportivos disponibles");
                    System.out.println("2. Hacer una reserva");
                    System.out.println("3. Cancelar una reserva");
                    System.out.println("4. Ver historial de reservas");
                    System.out.println("5. Cerrar sesión");

                    // Opciones adicionales para el administrador
                    if (currentUser instanceof Administrator) {
                        System.out.println("6. Agregar un espacio deportivo");
                        System.out.println("7. Modificar un espacio deportivo");
                        System.out.println("8. Eliminar un espacio deportivo");
                        System.out.println("9. Ver todas las reservas");
                    }

                    String userChoice = scanner.nextLine();

                    switch (userChoice) {
                        case "1" -> // Ver espacios deportivos disponibles
                            viewAvailableSpaces(reserveSystem);
                        case "2" -> // Hacer una reserva
                            makeReservation(scanner, reserveSystem, currentUser);
                        case "3" -> // Cancelar una reserva
                            cancelReservation(scanner, reserveSystem, currentUser);
                        case "4" -> // Ver historial de reservas
                            viewReservationHistory(reserveSystem, currentUser);
                        case "5" -> {
                            // Cerrar sesión
                            loggedIn = false;
                            currentUser = null;
                            System.out.println("Sesión cerrada.");
                        }
                        case "6" -> {
                            if (currentUser instanceof Administrator) {
                                // Agregar un espacio deportivo
                                addSportsSpace(scanner, reserveSystem, (Administrator) currentUser);
                            } else {
                                System.out.println("Opción no válida.");
                            }
                        }
                        case "7" -> {
                            if (currentUser instanceof Administrator) {
                                // Modificar un espacio deportivo
                                modifySportsSpace(scanner, reserveSystem, (Administrator) currentUser);
                            } else {
                                System.out.println("Opción no válida.");
                            }
                        }
                        case "8" -> {
                            if (currentUser instanceof Administrator) {
                                // Eliminar un espacio deportivo
                                removeSportsSpace(scanner, reserveSystem, (Administrator) currentUser);
                            } else {
                                System.out.println("Opción no válida.");
                            }
                        }
                        case "9" -> {
                            if (currentUser instanceof Administrator) {
                                // Ver todas las reservas
                                ((Administrator) currentUser).viewAllReservationHistory(reserveSystem);
                            } else {
                                System.out.println("Opción no válida.");
                            }
                        }
                        default -> System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    }
                }
            }
        }

        // Guardar datos antes de salir
        reserveSystem.saveReservations();
        reserveSystem.saveSportsSpaces();
        scanner.close();
    }

    // Método para registrar un nuevo usuario
    private static User register(Scanner scanner, ReserveSystem reserveSystem) {
        System.out.println("Registro de nuevo usuario");
        System.out.print("Ingrese su nombre: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese su correo electrónico: ");
        String email = scanner.nextLine();
        System.out.print("Cree una contraseña: ");
        String password = scanner.nextLine();

        System.out.println("Seleccione el tipo de usuario:");
        System.out.println("1. Cliente");
        System.out.println("2. Administrador");
        String userType = scanner.nextLine();

        int id = reserveSystem.generateUserId(); // Método para generar un ID único

        if (userType.equals("1")) {
            User user = new User(id, name, email);
            reserveSystem.addUser(user);
            System.out.println("Usuario registrado exitosamente.");
            return user;
        } else if (userType.equals("2")) {
            System.out.print("¿Es supervisor? (S/N): ");
            String isSupervisorInput = scanner.nextLine();
            boolean isSupervisor = isSupervisorInput.equalsIgnoreCase("S");
            Administrator admin = new Administrator(id, name, email, password, isSupervisor);
            reserveSystem.addUser(admin);
            System.out.println("Administrador registrado exitosamente.");
            return admin;
        } else {
            System.out.println("Tipo de usuario no válido.");
            return null;
        }
    }

    // Método para iniciar sesión
    private static User login(Scanner scanner, ReserveSystem reserveSystem) {
        System.out.println("Inicio de sesión");
        System.out.print("Ingrese su correo electrónico: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        User user = reserveSystem.authenticateUser(email, password);
        if (user != null) {
            System.out.println("Inicio de sesión exitoso.");
            return user;
        } else {
            System.out.println("Credenciales incorrectas. Por favor, intente de nuevo.");
            return null;
        }
    }

    // Método para ver espacios deportivos disponibles
    private static void viewAvailableSpaces(ReserveSystem reserveSystem) {
        List<SportsSpace> availableSpaces = reserveSystem.viewAvailableSpaces();
        if (availableSpaces.isEmpty()) {
            System.out.println("No hay espacios deportivos disponibles.");
        } else {
            System.out.println("Espacios deportivos disponibles:");
            for (SportsSpace space : availableSpaces) {
                System.out.println(space.getDetails());
            }
        }
    }

    // Método para hacer una reserva
    private static void makeReservation(Scanner scanner, ReserveSystem reserveSystem, User user) {
        System.out.println("Hacer una reserva");
        viewAvailableSpaces(reserveSystem);

        System.out.print("Ingrese el ID del espacio deportivo que desea reservar: ");
        int spaceId = Integer.parseInt(scanner.nextLine());

        SportsSpace space = reserveSystem.getSportsSpaceById(spaceId);
        if (space == null) {
            System.out.println("Espacio deportivo no encontrado.");
            return;
        }

        System.out.print("Ingrese la fecha de la reserva (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        System.out.print("Ingrese la hora de inicio (HH:MM): ");
        String startTimeInput = scanner.nextLine();
        System.out.print("Ingrese la hora de fin (HH:MM): ");
        String endTimeInput = scanner.nextLine();

        // Crear objetos Date y Time según sea necesario
        Date date = reserveSystem.parseDate(dateInput);
        Time startTime = reserveSystem.parseTime(startTimeInput);
        Time endTime = reserveSystem.parseTime(endTimeInput);

        if (space.isAvailable(date, startTime, endTime)) {
            int reservationId = reserveSystem.generateReservationId();
            Reservation reservation = new Reservation(reservationId, space, startTime, endTime, date, user);
            reserveSystem.makeReservation(reservation);
            System.out.println("Reserva realizada con éxito. ID de reserva: " + reservationId);
        } else {
            System.out.println("El espacio no está disponible en el horario seleccionado.");
        }
    }

    // Método para cancelar una reserva
    private static void cancelReservation(Scanner scanner, ReserveSystem reserveSystem, User user) {
        System.out.println("Cancelar una reserva");
        System.out.print("Ingrese el ID de la reserva que desea cancelar: ");
        int reservationId = Integer.parseInt(scanner.nextLine());

        if (reserveSystem.cancelReservation(reservationId, user)) {
            System.out.println("Reserva cancelada exitosamente.");
        } else {
            System.out.println("No se pudo cancelar la reserva. Verifique el ID y que sea su reserva.");
        }
    }

    // Método para ver el historial de reservas
    private static void viewReservationHistory(ReserveSystem reserveSystem, User user) {
        List<Reservation> reservations = reserveSystem.getReservationsByUser(user);
        if (reservations.isEmpty()) {
            System.out.println("No tiene reservas en su historial.");
        } else {
            System.out.println("Historial de reservas:");
            for (Reservation reservation : reservations) {
                System.out.println(reservation.getDetails());
            }
        }
    }

    // Método para agregar un espacio deportivo (Administrador)
    private static void addSportsSpace(Scanner scanner, ReserveSystem reserveSystem, Administrator admin) {
        System.out.println("Agregar un nuevo espacio deportivo");
        System.out.print("Ingrese el nombre del espacio: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el tipo de espacio (e.g., Fútbol, Baloncesto): ");
        String type = scanner.nextLine();
        System.out.print("Ingrese la capacidad: ");
        int capacity = Integer.parseInt(scanner.nextLine());

        int spaceId = reserveSystem.generateSportsSpaceId();
        SportsSpace space = new SportsSpace(spaceId, name, type, capacity);
        admin.addSportsSpace(reserveSystem, space);
    }

    // Método para modificar un espacio deportivo (Administrador)
    private static void modifySportsSpace(Scanner scanner, ReserveSystem reserveSystem, Administrator admin) {
        System.out.println("Modificar un espacio deportivo");
        System.out.print("Ingrese el ID del espacio a modificar: ");
        int spaceId = Integer.parseInt(scanner.nextLine());

        SportsSpace space = reserveSystem.getSportsSpaceById(spaceId);
        if (space == null) {
            System.out.println("Espacio deportivo no encontrado.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre del espacio (dejar en blanco para mantener): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            space.setName(name);
        }

        System.out.print("Ingrese el nuevo tipo de espacio (dejar en blanco para mantener): ");
        String type = scanner.nextLine();
        if (!type.isEmpty()) {
            space.setType(type);
        }

        System.out.print("Ingrese la nueva capacidad (dejar en blanco para mantener): ");
        String capacityInput = scanner.nextLine();
        if (!capacityInput.isEmpty()) {
            int capacity = Integer.parseInt(capacityInput);
            space.setCapacity(capacity);
        }

        admin.modifySportsSpace(reserveSystem, space);
    }

    // Método para eliminar un espacio deportivo (Administrador)
    private static void removeSportsSpace(Scanner scanner, ReserveSystem reserveSystem, Administrator admin) {
        System.out.println("Eliminar un espacio deportivo");
        System.out.print("Ingrese el ID del espacio a eliminar: ");
        int spaceId = Integer.parseInt(scanner.nextLine());

        admin.removeSportsSpace(reserveSystem, spaceId);
    }
}
