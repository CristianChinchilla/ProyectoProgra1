package proyectoprogra1;

import java.util.List;

public class Qualification {
    private double average; // Promedio de calificaciones
    private int totalRatings; // Total de calificaciones recibidas

    // Constructor
    public Qualification() {
        this.average = 0.0;
        this.totalRatings = 0;
    }

    // Getters
    public double getAverage() {
        return average;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    // Métodos clave

    /**
     * Calcula el promedio de calificaciones basado en una lista de calificaciones.
     *
     * @param ratings Lista de calificaciones (Rating)
     */
    public void calculateAverage(List<Rating> ratings) {
        if (ratings.isEmpty()) {
            this.average = 0.0;
            this.totalRatings = 0;
            return;
        }

        int sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getStars();
        }

        this.totalRatings = ratings.size();
        this.average = (double) sum / totalRatings;
    }

    /**
     * Actualiza la calificación promedio agregando una nueva calificación.
     *
     * @param newRating Nueva calificación a agregar
     */
    public void addRating(Rating newRating) {
        double total = (this.average * this.totalRatings) + newRating.getStars();
        this.totalRatings++;
        this.average = total / this.totalRatings;
    }

    /**
     * Elimina una calificación y recalcula el promedio.
     *
     * @param removedRating Calificación a eliminar
     * @param ratings Lista de todas las calificaciones restantes
     */
    public void removeRating(Rating removedRating, List<Rating> ratings) {
        ratings.remove(removedRating);
        calculateAverage(ratings); // Recalcula el promedio basado en la lista restante
    }

    @Override
    public String toString() {
        return "Qualification: " + average + " (Based on " + totalRatings + " ratings)";
    }
}
