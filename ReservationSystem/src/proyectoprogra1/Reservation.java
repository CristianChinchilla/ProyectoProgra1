package proyectoprogra1;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private static int idCounter = 1; // Contador estático para asignar IDs únicos a las reservas

    private int id;                     // ID único de la reserva
    private User user;                  // Usuario que realizó la reserva
    private SportsSpace sportsSpace;    // Espacio deportivo reservado
    private LocalDate date;             // Fecha de la reserva
    private LocalTime startTime;        // Hora de inicio de la reserva
    private LocalTime endTime;          // Hora de fin de la reserva
    private double price;               // Precio de la reserva
    private String status;              // Estado de la reserva (por ejemplo, "Confirmada", "Cancelada")

    // Constructor
    public Reservation(User user, SportsSpace sportsSpace, LocalDate date,
                       LocalTime startTime, LocalTime endTime, double price) {
        this.id = idCounter++;
        this.user = user;
        this.sportsSpace = sportsSpace;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.status = "Confirmada";
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public SportsSpace getSportsSpace() {
        return sportsSpace;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Método para obtener los detalles de la reserva
    public String getDetails() {
        return "ID de Reserva: " + id +
            "\nUsuario: " + user.getName() +
            "\nEspacio Deportivo: " + sportsSpace.getName() +
            "\nFecha: " + date.toString() +
            "\nHora: " + startTime.toString() + " - " + endTime.toString() +
            "\nPrecio: $" + price +
            "\nEstado: " + status;
    }
}
