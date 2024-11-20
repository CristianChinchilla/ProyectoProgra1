package proyectoprogra1;

public class Rating {
    private int id;          // ID único
    private int stars;       // Calificación en estrellas (1 a 5)
    private String comment;  // Comentario opcional
    private User user;       // Usuario que hizo la calificación

    public Rating(int id, int stars, String comment, User user) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException("Stars must be between 1 and 5.");
        }
        this.id = id;
        this.stars = stars;
        this.comment = comment;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public int getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

    public String getDetails(Language language) {
        if (language.getCode().equals("ES")) {
            return "Calificación: " + stars + " estrellas. Comentario: " + comment;
        } else {
            return "Rating: " + stars + " stars. Comment: " + comment;
        }
    }
}
