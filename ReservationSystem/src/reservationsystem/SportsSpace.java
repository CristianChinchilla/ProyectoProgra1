package reservationsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 
 * @author Eidan Alexandre Picado Leiva
 */

public class SportsSpace {

    private int id;
    private String name;
    private String type;
    private int capacity;
    private List<Schedule> availableSchedules;
    private List<Rating> ratings;
    private List<Comment> comments;
    private Qualification qualification;

    public SportsSpace(int id, String name, String type, int capacity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.availableSchedules = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.qualification = null; // O asigna una calificación predeterminada
    }

    // Método para verificar disponibilidad en un horario específico
    public boolean isAvailable(Date date, Time startTime, Time endTime) {
        for (Schedule schedule : availableSchedules) {
            if (schedule.conflictsWith(date, startTime, endTime)) {
                return false; // Hay un conflicto, no está disponible
            }
        }
        return true; // No hay conflictos, está disponible
    }

    // Método para agregar una valoración
    public Rating addRating(Rating rating) {
        ratings.add(rating);
        return rating;
    }

    // Método para agregar un comentario
    public Comment addComment(Comment comment) {
        comments.add(comment);
        return comment;
    }

    // Método para obtener la calificación promedio
    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0; // Si no hay calificacione

    boolean getDetails() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean getDetails() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
