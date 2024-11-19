/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogra1;

/**
 *
 * @author Eidan Alexandre Picado Leiva
 */
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        // Crear sistema de reservas
        ReserveSystem reserveSystem = new ReserveSystem();

        // Crear idiomas disponibles
        Language english = new Language("EN", "English");
        Language spanish = new Language("ES", "Español");

        // Crear usuarios con idiomas preferidos
        User user1 = new User(1, "Juan Pérez", "juan@example.com", spanish, NotificationType.EMAIL);
        User user2 = new User(2, "Alice Johnson", "alice@example.com", english, NotificationType.APP);

        // Crear espacios deportivos
        SportsSpace soccerField = new SportsSpace(1, "Cancha de Fútbol", "Fútbol", 20);

        // Agregar horarios disponibles a los espacios deportivos
        soccerField.getAvailableSchedules().add(new Schedule(LocalDate.of(2024, 11, 20), LocalTime.of(9, 0), LocalTime.of(11, 0)));

        // Intentar realizar una reserva para el usuario 1
        Reservation reservation1 = new Reservation(
            user1,
            soccerField,
            LocalDate.of(2024, 11, 20),
            LocalTime.of(9, 0),
            LocalTime.of(11, 0),
            20.0 // Precio de la reserva
        );

        boolean success1 = reserveSystem.makeReservation(reservation1);

        if (success1) {
            System.out.println(user1.getPreferredLanguage().getCode().equals("ES") ?
                    "¡Reserva realizada con éxito!" :
                    "Reservation completed successfully!");
        } else {
            System.out.println(user1.getPreferredLanguage().getCode().equals("ES") ?
                    "No se pudo realizar la reserva." :
                    "The reservation could not be completed.");
        }

        // Intentar realizar otra reserva para el usuario 2 en el mismo horario
        Reservation reservation2 = new Reservation(
            user2,
            soccerField,
            LocalDate.of(2024, 11, 20),
            LocalTime.of(9, 0),
            LocalTime.of(11, 0),
            20.0 // Precio de la reserva
        );

        boolean success2 = reserveSystem.makeReservation(reservation2);

        if (success2) {
            System.out.println(user2.getPreferredLanguage().getCode().equals("ES") ?
                    "¡Reserva realizada con éxito!" :
                    "Reservation completed successfully!");
        } else {
            System.out.println(user2.getPreferredLanguage().getCode().equals("ES") ?
                    "No se pudo realizar la reserva." :
                    "The reservation could not be completed.");
        }

        // Mostrar el historial de reservas
        System.out.println("\n" + (user1.getPreferredLanguage().getCode().equals("ES") ?
                "Historial de reservas para Juan Pérez:" :
                "Reservation history for Juan Pérez:"));
        user1.viewReservationHistory();

        System.out.println("\n" + (user2.getPreferredLanguage().getCode().equals("ES") ?
                "Historial de reservas para Alice Johnson:" :
                "Reservation history for Alice Johnson:"));
        user2.viewReservationHistory();
    }
}
