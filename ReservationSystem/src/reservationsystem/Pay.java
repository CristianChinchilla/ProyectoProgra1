package reservationsystem;

public class Pay {

    private String id;

    private String paymentMethod;

    private Reserve reservation;

    private Double amount;

    private Date paymentDate;

    public void processPayment() {
    }

    public void refund() {
    }

    public String getPaymentDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
