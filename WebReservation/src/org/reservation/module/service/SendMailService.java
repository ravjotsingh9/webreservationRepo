package org.reservation.module.service;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailService {
	public boolean sendNotification(String receiverId, String msg, String subject) throws MessagingException{
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", true); // added this line
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.user", "username");
		props.put("mail.smtp.password", "password");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", true);

		Session session = Session.getInstance(props, null);
		MimeMessage message = new MimeMessage(session);

		System.out.println("Port: " + session.getProperty("mail.smtp.port"));

		// Create the email addresses involved
		try {
			InternetAddress from = new InternetAddress("username");
			message.setSubject(subject);
			message.setFrom(from);
			message.addRecipients(Message.RecipientType.TO,
					InternetAddress.parse(receiverId));

			// Create a multi-part to combine the parts
			Multipart multipart = new MimeMultipart("alternative");

			// Create your text message part
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(msg);

			// Add the text part to the multipart
			multipart.addBodyPart(messageBodyPart);

			// Create the html part
			messageBodyPart = new MimeBodyPart();
			String htmlMessage = msg ;
			messageBodyPart.setContent(htmlMessage, "text/html");

			// Add html part to multi part
			multipart.addBodyPart(messageBodyPart);

			// Associate multi-part with message
			message.setContent(multipart);

			// Send message
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", "505520project@gmail.com",
					"justdoit505");
			System.out.println("Transport: " + transport.toString());
			transport.sendMessage(message, message.getAllRecipients());
      }catch (MessagingException mex) {
         throw new MessagingException(mex.getMessage());
      }
		return false;
	}
	
	/**
	 * This sendReserveNotification() operation sends email to the given id.
	 * @param receiverId
	 * @return true on success, else false
	 * @throws MessagingException 
	 */
	public boolean sendReserveNotification(String receiverId, String name, String confirmationNo, String category, String pick, String drop) throws MessagingException{
		SendMailService sendMail = new SendMailService();
		String msg ="Thank you"+name+", for renting with us! Your car is reserved. "+
				"\nYour Confirmation Number:"+confirmationNo+"\nVehicle Category:"+category+"Pickup Time:"+pick+"\tDrop Time:"+drop;
		String subject="SUPERRENT: Reservation Confirmation";
		try{
			sendMail.sendNotification(receiverId, msg, subject);
		}catch(MessagingException mex){
			throw new MessagingException(mex.getMessage());
		}
		return true;
	}
	
	/**
	 * This sendCancelNotification() operation sends email to the given id.
	 * @param receiverId
	 * @return true on success, else false
	 * @throws MessagingException 
	 */
	public boolean sendCancelNotification(String receiverId, String name, String confirmationNo) throws MessagingException{
		SendMailService sendMail = new SendMailService();
		String msg =name+", your reservation against Confirmation Number#"+confirmationNo+" has been cancelled. Please consider SUPERRENT for any of your future rental car needs.";
		String subject="SUPERRENT: Cancel Reservation Confirmation‏";
		try{
			sendMail.sendNotification(receiverId, msg, subject);
		}catch(MessagingException mex){
			throw new MessagingException(mex.getMessage());
		}
		return true;
	}
	
	public static void main (String args[]){
		SendMailService u = new SendMailService();
		try {
			boolean x = u.sendReserveNotification("ravjotsingh9@yahoo.com", "Ravjot", "70", "Truck","23/04/2014","27/04/2014");
			boolean y = u.sendCancelNotification("ravjotsingh9@yahoo.com", "Ravjot", "70");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
