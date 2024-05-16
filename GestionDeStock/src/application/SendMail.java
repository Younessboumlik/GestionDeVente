package application;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

		public static void main(String[] args) {
			

			Properties props = new Properties();
//	    	props.put("mail.smtp.user", "boulidamabdellah8@gmail.com");
//	    	props.put("mail.smtp.debug", "true");
	    	props.put("mail.smtp.host", "gmail.com");
	    	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//	        props.put("mail.smtp.auth", "true"); // Enable authentication if needed
	        props.put("mail.smtp.starttls.enable", "true"); // Enable TLS encryption
	        props.setProperty("mail.smtp.ssl.trust","*");
	        props.setProperty("mail.smtp.port", "25");
	        // Get mail session
	        Session session = Session.getInstance(props);

	        try {
	          // Create MimeMessage object
	          MimeMessage email = new MimeMessage(session);

	          // Set sender address
	          email.setFrom(new InternetAddress("admin@localserver.com"));

	          // Set recipient address	
	          email.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse("boulidamabdellah8@gmail.com"));

	          // Set subject
	          email.setSubject("code verification");

	          // Set message content (text/plain)
	          email.setContent("this is you code " + Math.random()*100, "text/plain; charset=utf-8");

	          // Send the email
	          System.out.println("lllllll");
	          Transport transport = session.getTransport("smtp");
	          transport.connect("sandbox.smtp.mailtrap.io", "boulidamabdellah8@gmail.com", "d6c113fabad2e1"); // Connect to SMTP server
	          transport.sendMessage(email, email.getAllRecipients()); // Send email
	          transport.close();

	          System.out.println("Email sent successfully!");

	        } catch (MessagingException e) {
	          e.printStackTrace();
	        }
    }
}
