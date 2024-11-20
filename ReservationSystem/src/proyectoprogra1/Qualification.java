package proyectoprogra1;

import java.util.ArrayList;
import java.util.List;

public class Qualification {
    private double average;          // Promedio de las calificaciones
    private int totalRatings;        // Total de calificaciones recibidas
    private List<Rating> ratings;    // Lista de calificaciones

    // Constructor
    public Qualification() {
        this.average = 0.0;
        this.totalRatings = 0;
        this.ratings = new ArrayList<>();
    }

    // Getters
    public double getAverage() {
        return average;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    // Métodos clave

    /**
     * Agrega una nueva calificación y actualiza el promedio.
     *
     * @param rating La calificación a agregar.
     */
    public void addRating(Rating rating) {
        ratings.add(rating);
        totalRatings++;
        recalculateAverage();
    }

    /**
     * Elimina una calificación por su ID y actualiza el promedio.
     *
     * @param ratingId El ID de la calificación a eliminar.
     * @return true si la calificación fue eliminada, false si no se encontró.
     */
    public boolean removeRating(int ratingId) {
        for (Rating rating : ratings) {
            if (rating.getId() == ratingId) {
                ratings.remove(rating);
                totalRatings--;
                recalculateAverage();
                return true;
            }
        }
        return false;
    }

    /**
     * Recalcula el promedio de las calificaciones actuales.
     */
    private void recalculateAverage() {
        if (ratings.isEmpty()) {
            average = 0.0;
        } else {
            double total = 0.0;
            for (Rating rating : ratings) {
                total += rating.getStars();
            }
            average = total / totalRatings;
        }
    }

    /**
     * Devuelve los detalles de las calificaciones en el idioma seleccionado.
     *
     * @param language Idioma en el que se generará el mensaje.
     * @return Cadena con los detalles de las calificaciones.
     */
    public String getDetails(Language language) {
        StringBuilder details = new StringBuilder();

        if (language.getCode().equals("ES")) {
            details.append("Calificaciones totales: ").append(totalRatings).append("\n");
            details.append("Promedio: ").append(String.format("%.2f", average)).append("\n");
            details.append("Lista de calificaciones:\n");
        } else {
            details.append("Total Ratings: ").append(totalRatings).append("\n");
            details.append("Average: ").append(String.format("%.2f", average)).append("\n");
            details.append("Rating List:\n");
        }

        for (Rating rating : ratings) {
            details.append("- ").append(rating.getDetails(language)).append("\n");
        }

        return details.toString();
    }

    @Override
    public String toString() {
        return "Qualification [Average=" + String.format("%.2f", average) + ", TotalRatings=" + totalRatings + "]";
    }
}
