package reservationsystem;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que representa un pago en el sistema de reservas.
 * 
 * @author Eidan Alexandre Picado Leiva
 */
public class Pay implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String paymentMethod; // Método de pago
    private Reservation reservation; // Reserva asociada al pago
    private double amount; // Monto a pagar
    private Date paymentDate; // Fecha del pago

    // Constructor
    public Pay(String id, String paymentMethod, Reservation reservation, double amount) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.reservation = reservation;
        this.amount = amount;
        this.paymentDate = new Date(); // Se asigna la fecha actual al realizar el pago
    }

    /**
     * Procesa el pago asociado a la reserva.
     * 
     * @return true si el pago fue exitoso, false en caso contrario.
     */
    public boolean processPayment() {
        // Simulación del procesamiento del pago
        try {
            System.out.println("Procesando el pago...");
            Thread.sleep(1000); // Simula un tiempo de procesamiento
            System.out.println("Pago de $" + amount + " realizado con éxito para la reserva ID: " + reservation.getId());
            return true;
        } catch (InterruptedException e) {
            System.out.println("Error al procesar el pago: " + e.getMessage());
            return false;
        }
    }

    /**
     * Reembolsa el pago realizado.
     * 
     * @return true si el reembolso fue exitoso, false en caso contrario.
     */
    public boolean refund() {
        // Simulación del procesamiento del reembolso
        try {
            System.out.println("Procesando el reembolso...");
            Thread.sleep(1000); // Simula un tiempo de procesamiento
            System.out.println("Reembolso de $" + amount + " realizado con éxito para la reserva ID: " + reservation.getId());
            return true;
        } catch (InterruptedException e) {
            System.out.println("Error al procesar el reembolso: " + e.getMessage());
            return false;
        }
    }

    /**
     * Devuelve los detalles del pago en formato legible.
     * 
     * @return Detalles del pago como String.
     */
    public String getPaymentDetails() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Pago ID: " + id +
               "\nMétodo de Pago: " + paymentMethod +
               "\nMonto: $" + amount +
               "\nFecha de Pago: " + dateFormat.format(paymentDate) +
               "\nReserva ID: " + reservation.getId();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
