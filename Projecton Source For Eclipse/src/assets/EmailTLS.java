package assets;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Collection;
import java.util.Properties;

/** 
 * 
 * @author Raviv Komem
 * This class is used to send emails using pre-existing system gmail address 
 */
public class EmailTLS {

	private static final String USERNAME = "braudeicm@gmail.com";
	private static final String PASSWORD = "ICM123456#";
	
	/** 
	 * This method is private method used to create the session for the mail
	 * @return Session
	 */
	private Session createSession()
	{
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); //TLS
       
		Session session = Session.getInstance(prop,
               new javax.mail.Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(USERNAME, PASSWORD);
                   }
		});
		
		return session;
	}
	
	/**
	 * This method will send a mail to the recipient and the bcc recipients
	 * The email will consists of the subject and body will be the message
	 * 
	 * @param toRecipient
	 * @param bccRecipient
	 * @param subject
	 * @param message
	 * @return message sent properly or not
	 */
	public boolean sendMessage(String toRecipient, String bccRecipient, String subject, String message)
	{
		Session session = this.createSession();
		try 
		{
			Message emailMessage = new MimeMessage(session);
	        emailMessage.setFrom(new InternetAddress(USERNAME));
	        emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toRecipient));
	        emailMessage.addRecipients(Message.RecipientType.CC, InternetAddress.parse(bccRecipient));
	        emailMessage.setSubject(subject);
	        emailMessage.setText(message);
	
	        Transport.send(emailMessage);
	
	        System.out.println("Sent mail to: "+toRecipient);
	        System.out.println("Sent mail bcc: "+bccRecipient);
	        return true;
		}
	        
		catch (MessagingException e) 
		{
            e.printStackTrace();
            return false;
        }
	}
	
	public boolean sendMessage(String toRecipient, String subject, String message)
	{	
		return this.sendMessage(toRecipient,"", subject, message);	
	}
	
	public boolean sendMessage(Collection<String> toRecipients, String subject, String message)
	{
		String toRecipientsList ="";
		for (String toRecipient : toRecipients)
		{
			toRecipientsList+=toRecipient+", ";
		}
		
		return this.sendMessage(toRecipientsList,"", subject, message);	
	}
	
	public boolean sendMessage(Collection<String> toRecipients, String bccRecipient, String subject, String message)
	{
		String toRecipientsList ="";
		for (String toRecipient : toRecipients)
		{
			toRecipientsList+=toRecipient+", ";
		}
		
		return this.sendMessage(toRecipientsList, bccRecipient, subject, message);	
	}
	
	public boolean sendMessage(String toRecipient, Collection<String> bccRecipients, String subject, String message)
	{	
		String bccRecipientsList ="";
		for (String bccRecipient : bccRecipients)
		{
			bccRecipientsList+=bccRecipient+", ";
		}
		
		return this.sendMessage(toRecipient, bccRecipientsList, subject, message);	
	}
	
	
	public boolean sendMessage(Collection<String> toRecipients, Collection<String> bccRecipients, String subject, String message)
	{
		String toRecipientsList ="";
		for (String toRecipient : toRecipients)
		{
			toRecipientsList+=toRecipient+", ";
		}
		
		String bccRecipientsList ="";
		for (String bccRecipient : bccRecipients)
		{
			bccRecipientsList+=bccRecipient+", ";
		}
		
		return this.sendMessage(toRecipientsList,bccRecipientsList, subject, message);	
	}

}