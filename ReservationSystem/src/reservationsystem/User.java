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
