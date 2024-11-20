package proyectoprogra1;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private int id;              // ID único del horario
    private LocalDate date;      // Fecha del horario
    private LocalTime startTime; // Hora de inicio
    private LocalTime endTime;   // Hora de fin
    private boolean isReserved;  // Estado del horario: reservado o disponible

    // Constructor
    public Schedule(int id, LocalDate date, LocalTime startTime, LocalTime endTime) {
    this.id = id; // Identificador único
    this.date = date;
    this.startTime = startTime;
    this.endTime = endTime;
    this.isReserved = false; // Por defecto, no reservado
}


    // Getters y Setters
    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isReserved() {
        return isReserved;
    }

    // Métodos clave

    /**
     * Verifica si el horario está disponible para la fecha y horas dadas.
     *
     * @param date      Fecha a verificar.
     * @param startTime Hora de inicio a verificar.
     * @param endTime   Hora de fin a verificar.
     * @return true si el horario está disponible, false de lo contrario.
     */
    public boolean isAvailable(LocalDate date, LocalTime startTime, LocalTime endTime) {
        return !isReserved &&
               this.date.equals(date) &&
               this.startTime.equals(startTime) &&
               this.endTime.equals(endTime);
    }

    /**
     * Marca este horario como reservado.
     */
    public void reserve() {
        if (!isReserved) {
            isReserved = true;
        }
    }

    /**
     * Libera este horario, marcándolo como disponible.
     */
    public void cancelReservation() {
        if (isReserved) {
            isReserved = false;
        }
    }

    /**
     * Verifica si un horario coincide exactamente con este horario.
     *
     * @param date      Fecha a comparar.
     * @param startTime Hora de inicio a comparar.
     * @param endTime   Hora de fin a comparar.
     * @return true si coinciden, false de lo contrario.
     */
    public boolean matches(LocalDate date, LocalTime startTime, LocalTime endTime) {
        return this.date.equals(date) &&
               this.startTime.equals(startTime) &&
               this.endTime.equals(endTime);
    }

    /**
     * Devuelve los detalles del horario en el idioma seleccionado.
     *
     * @param language Idioma en el que se generará el mensaje.
     * @return Cadena con los detalles del horario.
     */
    public String getDetails(Language language) {
        return language.getCode().equals("ES") ?
                "Horario #" + id +
                "\nFecha: " + date +
                "\nHora: " + startTime + " - " + endTime +
                "\nEstado: " + (isReserved ? "Reservado" : "Disponible") :
                "Schedule #" + id +
                "\nDate: " + date +
                "\nTime: " + startTime + " - " + endTime +
                "\nStatus: " + (isReserved ? "Reserved" : "Available");
    }

    @Override
    public String toString() {
        return "Schedule #" + id + " [" + date + " " + startTime + "-" + endTime + "] - " + (isReserved ? "Reserved" : "Available");
    }
}