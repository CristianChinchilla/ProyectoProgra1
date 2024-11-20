package proyectoprogra1;

public class Language {
    private String code; // Código del idioma (e.g., "EN", "ES")
    private String name; // Nombre del idioma (e.g., "English", "Español")

    // Constructor
    public Language(String code, String name) {
        this.code = code.toUpperCase(); // Aseguramos que el código esté en mayúsculas
        this.name = name;
    }

    // Getters y Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code.toUpperCase(); // Aseguramos que siempre se guarde en mayúsculas
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Métodos clave

    /**
     * Devuelve el nombre y código del idioma en formato legible.
     *
     * @return String con el formato "Nombre (Código)"
     */
    public String getDetails() {
        return name + " (" + code + ")";
    }

    /**
     * Verifica si este idioma coincide con un código proporcionado.
     *
     * @param code Código del idioma a verificar.
     * @return true si coinciden, false de lo contrario.
     */
    public boolean matches(String code) {
        return this.code.equalsIgnoreCase(code);
    }

    @Override
    public String toString() {
        return getDetails();
    }
}
