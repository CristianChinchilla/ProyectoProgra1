/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogra1;

/**
 *
 * @author Eidan Alexandre Picado Leiva
 * @author Cristian Gerardo Chichilla Fonseca
 * @author Jefferson Alexander Soto Ulate
 */
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TelegramNotifier extends TelegramLongPollingBot {
    private final String botToken;      // Token del bot
    private final String botUsername;  // Username del bot

    // Constructor
    public TelegramNotifier(String botToken, String botUsername) {
        this.botToken = botToken;
        this.botUsername = botUsername;
    }

    // Métodos abstractos sobrescritos de TelegramLongPollingBot
    @Override
    public void onUpdateReceived(Update update) {
        // Manejo de mensajes entrantes desde los usuarios (opcional)
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String incomingMessage = update.getMessage().getText();
            System.out.println("Mensaje recibido: " + incomingMessage);

            // Respuesta automática (opcional)
            sendMessage(chatId, "Gracias por tu mensaje. Este bot envía notificaciones.");
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    // Métodos clave

    /**
     * Enviar notificaciones de reserva con soporte multilingüe.
     *
     * @param chatId       ID del chat de Telegram del destinatario.
     * @param reservation  Objeto Reservation que contiene los detalles de la reserva.
     * @param language     Idioma en el que se generará el mensaje.
     */
    public void sendReservationNotification(String chatId, Reservation reservation, Language language) {
        String message;

        // Crear mensaje basado en el idioma
        if (language.getCode().equals("ES")) {
            message = "¡Reserva confirmada!\n" +
                      "Espacio: " + reservation.getSportsSpace().getName() + "\n" +
                      "Fecha: " + reservation.getDate() + "\n" +
                      "Hora: " + reservation.getStartTime() + " a " + reservation.getEndTime() + ".";
        } else {
            message = "Reservation confirmed!\n" +
                      "Space: " + reservation.getSportsSpace().getName() + "\n" +
                      "Date: " + reservation.getDate() + "\n" +
                      "Time: " + reservation.getStartTime() + " to " + reservation.getEndTime() + ".";
        }

        // Enviar mensaje
        sendMessage(chatId, message);
    }

    /**
     * Enviar mensajes genéricos a un chat de Telegram.
     *
     * @param chatId  ID del chat de Telegram del destinatario.
     * @param message Mensaje a enviar.
     */
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            execute(sendMessage); // Enviar el mensaje
            System.out.println("Mensaje enviado a Telegram: " + message);
        } catch (TelegramApiException e) {
            System.err.println("Error al enviar mensaje: " + e.getMessage());
        }
    }

    /**
     * Enviar notificaciones personalizadas según el tipo.
     *
     * @param chatId   ID del chat de Telegram del destinatario.
     * @param type     Tipo de notificación.
     * @param content  Contenido adicional para la notificación.
     * @param language Idioma en el que se generará el mensaje.
     */
    public void sendCustomNotification(String chatId, NotificationType type, String content, Language language) {
        String message;

        // Crear mensaje basado en el tipo y el idioma
        switch (type) {
            case EMAIL:
                message = language.getCode().equals("ES") ?
                        "Nueva notificación por correo:\n" + content :
                        "New email notification:\n" + content;
                break;
            case APP:
                message = language.getCode().equals("ES") ?
                        "Nueva notificación en la aplicación:\n" + content :
                        "New app notification:\n" + content;
                break;
            case SMS:
                message = language.getCode().equals("ES") ?
                        "Nueva notificación por SMS:\n" + content :
                        "New SMS notification:\n" + content;
                break;
            default:
                message = language.getCode().equals("ES") ?
                        "Notificación desconocida:\n" + content :
                        "Unknown notification:\n" + content;
        }

        // Enviar mensaje
        sendMessage(chatId, message);
    }
}