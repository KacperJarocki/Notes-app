package server.form;

public interface EmailSenderService {
    void sendEmail(String from, String subject, String message);
}
