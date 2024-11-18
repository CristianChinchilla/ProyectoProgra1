package reservationsystem;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que representa un pago en el sistema de reservas.
 * 
 * @author
 */
public class Pay implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String paymentMethod;
    private Reservation reservation;
    private double amount;
    private Date paymentDate;

    // Constructor
    public Pay(String id, String paymentMethod, Reservation reservation, double amount, Date paymentDate) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.reservation = reservation;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    // Método para procesar el pago
    public boolean processPayment() {
        // Aquí iría la lógica para procesar el pago con una pasarela de pago
        // Simularemos que el pago siempre es exitoso
        System.out.println("Procesando el pago...");
        System.out.println("Pago de $" + amount + " realizado con éxito para la reserva ID: " + reservation.getId());
        return true;
    }

    // Método para reembolsar el pago
    public boolean refund() {
        // Aquí iría la lógica para procesar el reembolso
        // Simularemos que el reembolso siempre es exitoso
        System.out.println("Procesando el reembolso...");
        System.out.println("Reembolso de $" + amount + " realizado con éxito para la reserva ID: " + reservation.getId());
        return true;
    }

    // Método para obtener los detalles del pago
    public String getPaymentDetails() {
        return "Pago ID: " + id +
               "\nMétodo de Pago: " + paymentMethod +
               "\nMonto: $" + amount +
               "\nFecha de Pago: " + paymentDate +
               "\nReserva ID: " + reservation.getId();
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public double getAmount() {
        return amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
