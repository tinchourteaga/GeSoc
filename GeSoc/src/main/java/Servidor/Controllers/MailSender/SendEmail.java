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
        String sender = "orggesoc";

        String clave = "PONER LA CLAVE DEL MAIL orggesoc";

        // Getting system properties
        Properties properties = new Properties();

        // Setting up mail server
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.user", sender);
        properties.put("mail.smtp.clave", clave);

        // creating session object to get properties
        Session session = Session.getDefaultInstance(properties);

        // MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        try
        {
            // Set From Field: adding senders email to from field.
            message.setFrom(new InternetAddress(sender));

            // Set To Field: adding recipient's email to from field.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: subject of the email
            message.setSubject("Usuario y contrasenia");

            // set body of the email.
            message.setText("Su usario es: " + usuario);
            message.setText("Su password es: " + contrasenia);

            Transport transport = session.getTransport("smtp");

            transport.connect("smtp.gmail.com", sender, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            // Send email.
            // Transport.send(message);
            System.out.println("Mail enviado...");
        }
        catch (MessagingException mex)
        {
            mex.printStackTrace();
        }
    }
}