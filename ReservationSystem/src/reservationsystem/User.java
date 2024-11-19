package reservationsystem;
import java.util.Scanner;

public class User {

    private int id;

    private String name;

    private String email;

    private Language preferredLanguage;

    private NotificationType preferredType;

    private boolean IsAdmin;

    Scanner sc = new Scanner(System.in);

    public User(int id, String name, String email, Language preferredLanguage, NotificationType preferredType, boolean IsAdmin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.preferredLanguage = preferredLanguage;
        this.preferredType = preferredType;
        this.IsAdmin = IsAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Language getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(Language preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public NotificationType getPreferredType() {
        return preferredType;
    }

    public void setPreferredType(NotificationType preferredType) {
        this.preferredType = preferredType;
    }

    public boolean isIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(boolean IsAdmin) {
        this.IsAdmin = IsAdmin;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }


    public void selectTypeUser() {
        System.out.println("""
                Escoga el tipo de usuario:
                1. Cliente
                2. Administrador
                """);
        String selection = sc.next();

        switch (selection) {
            case "1" -> {
                IsAdmin = true;
            }
            case "2" -> {
                IsAdmin = false;
            }
            default -> {
                System.out.println("Opción no valida");
                selectTypeUser();
            }
        }
    }

    public void chooseNotificationType() {
    }
}
