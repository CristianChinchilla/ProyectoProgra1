package proyectoprogra1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SportsSpace {
    private int id;                         // ID único del espacio deportivo
    private String name;                    // Nombre del espacio deportivo
    private String type;                    // Tipo de espacio (ejemplo: cancha, gimnasio)
    private int capacity;                   // Capacidad del espacio
    private List<Schedule> availableSchedules; // Lista de horarios disponibles
    private List<Rating> ratings;           // Lista de calificaciones
    private List<Comment> comments;         // Lista de comentarios
    private Qualification qualification;    // Calificación general del espacio

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

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Schedule> getAvailableSchedules() {
        return availableSchedules;
    }

    public Qualification getQualification() {
        return qualification;
    }

    // Métodos clave

    /**
     * Verifica si el espacio está disponible para la fecha y horas dadas.
     *
     * @param date      Fecha a verificar.
     * @param startTime Hora de inicio a verificar.
     * @param endTime   Hora de fin a verificar.
     * @return true si está disponible, false de lo contrario.
     */
    public boolean isAvailable(LocalDate date, LocalTime startTime, LocalTime endTime) {
        for (Schedule schedule : availableSchedules) {
            if (schedule.isAvailable(date, startTime, endTime)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Agrega un horario disponible al espacio.
     *
     * @param schedule Horario a agregar.
     */
    public void addSchedule(Schedule schedule) {
        availableSchedules.add(schedule);
    }

    /**
     * Marca un horario como reservado para una reserva específica.
     *
     * @param reservation Reserva asociada al horario.
     */
    public void markAsReserved(Reservation reservation) {
        for (Schedule schedule : availableSchedules) {
            if (schedule.matches(reservation.getDate(), reservation.getStartTime(), reservation.getEndTime())) {
                schedule.reserve();
                break;
            }
        }
    }

    /**
     * Libera un horario, marcándolo como disponible.
     *
     * @param reservation Reserva asociada al horario.
     */
    public void unmarkReservation(Reservation reservation) {
        for (Schedule schedule : availableSchedules) {
            if (schedule.matches(reservation.getDate(), reservation.getStartTime(), reservation.getEndTime())) {
                schedule.cancelReservation();
                break;
            }
        }
    }

    /**
     * Agrega una calificación al espacio y actualiza la calificación general.
     *
     * @param rating Calificación a agregar.
     */
    public void addRating(Rating rating) {
        ratings.add(rating);
        qualification.addRating(rating);
    }

    /**
     * Agrega un comentario al espacio deportivo.
     *
     * @param comment Comentario a agregar.
     */
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    /**
     * Obtiene los detalles del espacio deportivo en el idioma seleccionado.
     *
     * @param language Idioma en el que se generará el mensaje.
     * @return Cadena con los detalles del espacio.
     */
    public String getDetails(Language language) {
        StringBuilder details = new StringBuilder();

        if (language.getCode().equals("ES")) {
            details.append("Espacio Deportivo #").append(id)
                   .append("\nNombre: ").append(name)
                   .append("\nTipo: ").append(type)
                   .append("\nCapacidad: ").append(capacity)
                   .append("\nPromedio de Calificaciones: ").append(qualification.getAverage())
                   .append("\nHorarios Disponibles:\n");
        } else {
            details.append("Sports Space #").append(id)
                   .append("\nName: ").append(name)
                   .append("\nType: ").append(type)
                   .append("\nCapacity: ").append(capacity)
                   .append("\nAverage Rating: ").append(qualification.getAverage())
                   .append("\nAvailable Schedules:\n");
        }

        for (Schedule schedule : availableSchedules) {
            details.append("- ").append(schedule.getDetails(language)).append("\n");
        }

        return details.toString();
    }

    @Override
    public String toString() {
        return "SportsSpace #" + id + " [" + name + " - " + type + "]";
    }
}