package proyectoprogra1;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private static int idCounter = 1; // Contador estático para IDs únicos

    private int id;                     // ID único de la reserva
    private User user;                  // Usuario que realizó la reserva
    private SportsSpace sportsSpace;    // Espacio deportivo reservado
    private LocalDate date;             // Fecha de la reserva
    private LocalTime startTime;        // Hora de inicio de la reserva
    private LocalTime endTime;          // Hora de fin de la reserva
    private double price;               // Precio de la reserva
    private String status;              // Estado de la reserva

    public Reservation(User user, SportsSpace sportsSpace, LocalDate date,
                       LocalTime startTime, LocalTime endTime, double price) {
        this.id = idCounter++;
        this.user = user;
        this.sportsSpace = sportsSpace;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.status = "Confirmed";
    }
    
    public String getStatus() {
    return status;
}

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

    public String getDetails(Language language) {
        return (language.getCode().equals("ES") ?
                "ID de Reserva: " + id + "\nUsuario: " + user.getName() + "\nEspacio Deportivo: " + sportsSpace.getName() +
                "\nFecha: " + date + "\nHora: " + startTime + " - " + endTime + "\nPrecio: $" + price :
                "Reservation ID: " + id + "\nUser: " + user.getName() + "\nSports Space: " + sportsSpace.getName() +
                "\nDate: " + date + "\nTime: " + startTime + " - " + endTime + "\nPrice: $" + price);
    }
}
