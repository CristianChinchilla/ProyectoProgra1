package proyectoprogra1;

public class Comment {
    private int id;             // ID único del comentario
    private String text;        // Contenido del comentario
    private User user;          // Usuario que realizó el comentario
    private Reservation reservation; // Reserva asociada al comentario (opcional)

    // Constructor
    public Comment(int id, String text, User user, Reservation reservation) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.reservation = reservation;
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

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    // Métodos clave
    public String getDetails(Language language) {
        if (language.getCode().equals("ES")) {
            return "Comentario #" + id +
                   "\nUsuario: " + user.getName() +
                   "\nReserva asociada: " + (reservation != null ? reservation.getId() : "Sin reserva") +
                   "\nTexto: " + text;
        } else {
            return "Comment #" + id +
                   "\nUser: " + user.getName() +
                   "\nAssociated Reservation: " + (reservation != null ? reservation.getId() : "No reservation") +
                   "\nText: " + text;
        }
    }

    @Override
    public String toString() {
        return "Comment ID: " + id + " by " + user.getName();
    }
}
