package proyectoprogra1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SportsSpace {
    private int id;                      // ID único del espacio deportivo
    private String name;                 // Nombre del espacio deportivo
    private String type;                 // Tipo de espacio (ejemplo: cancha, gimnasio)
    private int capacity;                // Capacidad del espacio
    private List<Schedule> availableSchedules; // Lista de horarios disponibles
    private List<Rating> ratings;        // Lista de calificaciones
    private List<Comment> comments;      // Lista de comentarios
    private Qualification qualification; // Calificación general

    // Constructor
    public SportsSpace(int id, String name, String type, int capacity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.availableSchedules = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.qualification = new Qualification();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Schedule> getAvailableSchedules() {
        return availableSchedules;
    }

    public Qualification getQualification() {
        return qualification;
    }

    // Métodos clave
    public boolean isAvailable(LocalDate date, LocalTime startTime, LocalTime endTime) {
        for (Schedule schedule : availableSchedules) {
            if (schedule.isAvailable(date, startTime, endTime)) {
                return true;
            }
        }
        return false;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
        qualification.calculateAverage(ratings);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public double getAverageRating() {
        return qualification.getAverage();
    }

    public void markAsReserved(Reservation reservation) {
        for (Schedule schedule : availableSchedules) {
            if (schedule.matches(reservation.getDate(), reservation.getStartTime(), reservation.getEndTime())) {
                schedule.reserve();
            }
        }
    }

    public void unmarkReservation(Reservation reservation) {
        for (Schedule schedule : availableSchedules) {
            if (schedule.matches(reservation.getDate(), reservation.getStartTime(), reservation.getEndTime())) {
                schedule.cancelReservation();
            }
        }
    }
}
