package reservationsystem;

import java.util.List;

public class Administrator extends User {

    private boolean isSupervisor;
    private String password;

    // Constructor
    public Administrator(int id, String name, String email, String password, boolean isSupervisor) {
        super(id, name, email);
        this.password = password;
        this.isSupervisor = isSupervisor;
    }

    // Método para agregar un nuevo espacio deportivo
    public SportsSpace addSportsSpace(ReserveSystem system, SportsSpace space) {
        system.addSportsSpace(space);
        System.out.println("Espacio deportivo agregado exitosamente.");
        return space;
    }

    // Método para modificar un espacio deportivo existente
    public void modifySportsSpace(ReserveSystem system, SportsSpace updatedSpace) {
        if (system.updateSportsSpace(updatedSpace)) {
            System.out.println("Espacio deportivo modificado exitosamente.");
        } else {
            System.out.println("No se encontró el espacio deportivo con ID: " + updatedSpace.getId());
        }
    }

    // Método para eliminar un espacio deportivo
    public void removeSportsSpace(ReserveSystem system, int spaceID) {
        if (system.deleteSportsSpace(spaceID)) {
            System.out.println("Espacio deportivo eliminado exitosamente.");
        } else {
            System.out.println("No se encontró el espacio deportivo con ID: " + spaceID);
        }
    }

    // Método para ver todas las reservas realizadas
    public List<Reservation> viewAllReservationHistory(ReserveSystem system) {
        List<Reservation> reservations = system.viewReservationHistory();
        for (Reservation reservation : reservations) {
            System.out.println(reservation.getDetails());
        }
        return reservations;
    }

    // Método para editar reservas
    public void editReservation(ReserveSystem system, int reservationId, Reservation newReservation) {
        if (system.updateReservation(reservationId, newReservation)) {
            System.out.println("Reserva actualizada exitosamente.");
        } else {
            System.out.println("No se pudo actualizar la reserva con ID: " + reservationId);
        }
    }
}
