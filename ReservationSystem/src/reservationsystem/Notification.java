package reservationsystem;

import java.util.Date;

public class Notification {

    private int id;

    private User user;

    private String message;

    private Date sendDate;

    private NotificationType type;

    public void send() {
    }

    public void schedule(Date sendDate) {
    }

    public String getNotificationDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
