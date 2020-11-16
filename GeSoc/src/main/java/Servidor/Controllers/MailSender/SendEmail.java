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

        String clave = "PONER LA CLAVE DE ";

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
            message.setSubject("Usuario y Contraseña - GeSoc");

            // set body of the email.
            message.setContent("Este email fue enviado para comunicarle su usuario y contraseña:<br><br>" +
                    "<b>USUARIO:</b> " + usuario + "<br>" +
                    "<b>CONTRASEÑA:</b> " + contrasenia + "<br><br>" +
                    "Si no solicitó este email, simplemente ignorelo.", "text/html");

            // Send email.
            Transport transport = session.getTransport("smtp");

            transport.connect("smtp.gmail.com", sender, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("Mail enviado...");
        }
        catch (MessagingException mex)
        {
            mex.printStackTrace();
        }
    }
}