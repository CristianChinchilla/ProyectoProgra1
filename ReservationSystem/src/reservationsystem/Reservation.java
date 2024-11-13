/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reservationsystem;

/**
 *
 * @author eidan
 */
class Reservation {
  private int id;
    private SportsSpace space;
    private String startTime;
    private String endTime;
    // otros atributos de la reserva

    // Constructor y otros métodos

    // Método getter para el ID
    public int getId() {
        return id;
    }

    // Método para verificar conflictos de horario
    public boolean conflictsWith(Reservation other) {
        if (!this.space.equals(other.space)) {
            return false;
        }
        return (this.startTime.compareTo(other.endTime) < 0 && this.endTime.compareTo(other.startTime) > 0);
    }

    int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}