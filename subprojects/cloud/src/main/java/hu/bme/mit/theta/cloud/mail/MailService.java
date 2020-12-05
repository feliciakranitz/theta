package hu.bme.mit.theta.cloud.mail;

import hu.bme.mit.theta.cloud.repository.datamodel.JobEntity;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {

    public void sendMail(JobEntity job, String status) {
        // Recipient's email ID needs to be mentioned.
        String to = job.getNotificationAddress();

        // Sender's email ID needs to be mentioned
        String from = "cloudthetathesis@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.user", "cloudthetathesis@gmail.com");
        properties.setProperty("mail.password", "ThetaCloudP@ssw0rd");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("cloudthetathesis@gmail.com", "ThetaCloudP@ssw0rd");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("[" + job.getJobId() + "] Theta analysis " + status.toLowerCase() );

            // Now set the actual message
            message.setText("Your analysis on model " + job.getModel().getFileName() + " " + (status.equals("FAILED") ? status : "is completed") +
                    ((status.equals("COMPLETED")) ? ("\n Result: " + (job.isSafe() ? "Safe" : "Unsafe")) : ""));

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
