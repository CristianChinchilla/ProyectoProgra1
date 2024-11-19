/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoprogra1;

/**
 *
 * @author eidan
 */m
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

    @Override
    public void onUpdateReceived(Update update) {
        // Manejo de mensajes entrantes (opcional)
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    // Método para enviar mensajes con soporte de idioma
    public void sendReservationNotification(String chatId, String languageCode, String spaceName, String date, String startTime, String endTime) {
        String message;

        // Mensaje basado en el idioma preferido
        if (languageCode.equalsIgnoreCase("ES")) {
            message = "¡Reserva confirmada!\n" +
                      "Espacio: " + spaceName + "\n" +
                      "Fecha: " + date + "\n" +
                      "Hora: " + startTime + " a " + endTime + ".";
        } else { // Default a inglés
            message = "Reservation confirmed!\n" +
                      "Space: " + spaceName + "\n" +
                      "Date: " + date + "\n" +
                      "Time: " + startTime + " to " + endTime + ".";
        }

        // Enviar el mensaje a Telegram
        sendMessage(chatId, message);
    }

    // Método genérico para enviar mensajes
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
}
