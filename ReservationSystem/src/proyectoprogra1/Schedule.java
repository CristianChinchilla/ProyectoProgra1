package proyectoprogra1;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private LocalDate date;       // Fecha del horario
    private LocalTime startTime;  // Hora de inicio
    private LocalTime endTime;    // Hora de fin
    private boolean isReserved;   // Indica si el horario está reservado

    // Constructor
    public Schedule(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isReserved = false; // Por defecto, el horario está disponible
    }

    // Getters y Setters
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean isReserved() {
        return isReserved;
    }

    // Métodos clave
    public boolean isAvailable(LocalDate date, LocalTime startTime, LocalTime endTime) {
        // Verifica si el horario está disponible y si las horas coinciden
        return !isReserved && this.date.equals(date) &&
               this.startTime.equals(startTime) && this.endTime.equals(endTime);
    }

    public void reserve() {
        this.isReserved = true; // Marca el horario como reservado
    }

    public void cancelReservation() {
        this.isReserved = false; // Marca el horario como disponible
    }

    public boolean matches(LocalDate date, LocalTime startTime, LocalTime endTime) {
        // Verifica si un horario específico coincide con este objeto
        return this.date.equals(date) &&
               this.startTime.equals(startTime) && this.endTime.equals(endTime);
    }
}
