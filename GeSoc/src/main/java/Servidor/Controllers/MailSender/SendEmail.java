package Servidor.Controllers.MailSender;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;



public class SendEmail
{

    public static void main(String mailUsuario, String usuario, String contrasenia)
    {
        // email ID of Recipient.
        String recipient = mailUsuario;

        // email ID of  Sender.
        String sender = "orggesoc@gmail.com";

        // using host as localhost
        String host = "127.0.0.1";

        // Getting system properties
        Properties properties = System.getProperties();

        // Setting up mail server
        properties.setProperty("mail.smtp.host", host);

        // creating session object to get properties
        Session session = Session.getDefaultInstance(properties);

        try
        {
            // MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From Field: adding senders email to from field.
            message.setFrom(new InternetAddress(sender));

            // Set To Field: adding recipient's email to from field.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: subject of the email
            message.setSubject("Usuario y contrasenia");

            // set body of the email.
            message.setText("Su usario es: " + usuario);
            message.setText("Su password es: " + contrasenia);

            // Send email.
            Transport.send(message);
            System.out.println("Mail enviado...");
        }
        catch (MessagingException mex)
        {
            mex.printStackTrace();
        }
    }
}