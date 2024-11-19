package proyectoprogra1;

public class Rating {
    private int id;           // ID único de la calificación
    private int stars;        // Calificación en estrellas (1 a 5)
    private String comment;   // Comentario opcional del usuario
    private User user;        // Usuario que realiza la calificación

    // Constructor
    public Rating(int id, int stars, String comment, User user) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5 stars.");
        }
        this.id = id;
        this.stars = stars;
        this.comment = comment;
        this.user = user;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5 stars.");
        }
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Métodos clave
    @Override
    public String toString() {
        return "Rating by " + user.getName() + ": " +
               stars + " stars\nComment: " + comment;
    }
}
