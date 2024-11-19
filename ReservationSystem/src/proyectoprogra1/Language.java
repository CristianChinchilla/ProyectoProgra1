package proyectoprogra1;

public class Language {
    private String code; // Código del idioma (por ejemplo, "EN", "ES", "FR")
    private String name; // Nombre del idioma (por ejemplo, "English", "Español")

    // Constructor
    public Language(String code, String name) {
        this.code = code;
        this.name = name;
    }

    // Getters y Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Métodos clave
    public String toString() {
        return name + " (" + code + ")";
    }

    public boolean matches(String code) {
        return this.code.equalsIgnoreCase(code);
    }
}
