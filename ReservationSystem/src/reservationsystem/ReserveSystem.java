package reservationsystem;

import java.io.File;
import java.util.List;

class ReserveSystem {

    private File file = new File("Reservations");

    //private int reservations;

    private List<Reservations> reservations;

    private Lenguage lenguage;


    public Reservation saveReserves(Reservations reservations) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Reservations> loadReservations() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void register() {
    }

    public void login() {
    }

    public void logout() {
    }

    public Language chooseLanguage(Language language) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<SportsSpace> viewAvailableSpaces() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Reservation makeReservation(Reservation reservation) {
        if (file.exists()){
            Reservations reserve = new Reservations();
        }
        return null;
    }

    public int cancelReservation(int reservationId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Reservation> viewReservationHistory() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Reservation shareReservationOnSocialMedia(Reservation reservation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
