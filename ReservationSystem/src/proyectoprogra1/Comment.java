package proyectoprogra1;

public class Comment {
    private int id;           // ID único del comentario
    private String text;      // Contenido del comentario
    private User user;        // Usuario que realizó el comentario
    private int relatedRatingId; // ID de la calificación relacionada, si existe

    // Constructor
    public Comment(int id, String text, User user, int relatedRatingId) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.relatedRatingId = relatedRatingId;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRelatedRatingId() {
        return relatedRatingId;
    }

    public void setRelatedRatingId(int relatedRatingId) {
        this.relatedRatingId = relatedRatingId;
    }

    // Métodos clave
    @Override
    public String toString() {
        return "Comment by " + user.getName() + 
               " (Rating ID: " + (relatedRatingId > 0 ? relatedRatingId : "No Rating") + "):\n" + 
               text;
    }
}
