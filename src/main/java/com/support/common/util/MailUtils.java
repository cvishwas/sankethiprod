package com.support.common.util;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.support.common.vo.EmailAttachment;



public class MailUtils {
	private static Logger LOGGER = Logger.getLogger(MailUtils.class);


	public static boolean sendEmail(Map<String, Object> mailMap) {
		  boolean flag = true;
	      // Recipient's email ID needs to be mentioned.
	      String to = (String)mailMap.get("to");
	      String cc = (String)mailMap.get("cc");

	      // Sender's email ID needs to be mentioned
	      String from = (String)mailMap.get("from");
	      
	      String bounceAddr = mailMap.get("bounceAddr") != null?(String)mailMap.get("bounceAddr"):"";

	      final String username = (String)mailMap.get("username");
	      final String password = (String)mailMap.get("password");

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = (String)mailMap.get("host");
	      String port = (mailMap.get("port") != null) ? (String)mailMap.get("port") : "25";
	      String auth = (mailMap.get("auth") != null) ? (String)mailMap.get("auth") : "false"; // "true"\"false"
	      String starttls = (mailMap.get("starttls") != null) ? (String)mailMap.get("starttls") : "false"; // "true"\"false"
	      
	      String subject = (String)mailMap.get("subject");
	      String content = (String)mailMap.get("content");
	      
	      List<EmailAttachment> attatchments = (List<EmailAttachment>)mailMap.get("mailAttachment");
	    
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", auth);
	      props.put("mail.smtp.starttls.enable", starttls);
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", port);
	      if(!bounceAddr.equals("") && bounceAddr.indexOf("@") > 0){
	    	  props.put("mail.smtp.from", bounceAddr);
	      }

	      // Get the Session object.
	      Session session = null;
	      if(username !=null && password != null){
	      
		      session = Session.getInstance(props,
		         new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		               return new PasswordAuthentication(username, password);
		            }
		       });
	      }else{
	    	  session = Session.getDefaultInstance(props);
	      }
	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(to));
	         
	         if(StringUtils.isNotEmpty(cc))
	        	 message.setRecipients(Message.RecipientType.CC,
	     	            InternetAddress.parse(cc));

	         // Set Subject: header field
	         message.setSubject(subject);

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setContent(content, "text/html; charset=utf-8");
	        // messageBodyPart.setText(content);

	         // Create a multipar message
	         //Multipart multipart = new MimeMultipart();
	         Multipart multipart = createBody(messageBodyPart,attatchments);
	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);
             
	         LOGGER.info("Sent message successfully....");
	  
	      } catch (MessagingException e) {
	    	  LOGGER.error(e.getMessage(),e);
	    	  flag = false;
	      }
	      return flag;
	   }
	
	private static Multipart createBody(BodyPart messageBodyPart,List<EmailAttachment> attatchments) throws MessagingException{
		// Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        if(null != attatchments){
	        for(EmailAttachment attachment : attatchments){
	        	byte [] fileBytes = convertFiletoByteArray(attachment.getFileContent());
	            String fileName = attachment.getFileName();
	            String contentType = attachment.getContentType();
	            if(fileBytes != null){
	    	         DataSource dataSource = new ByteArrayDataSource(fileBytes, contentType);
	    	         messageBodyPart = new MimeBodyPart();
	    	         messageBodyPart.setDataHandler(new DataHandler(dataSource));
	    	         messageBodyPart.setFileName(fileName);
	    	         multipart.addBodyPart(messageBodyPart);
	            }
	        }
        }
        return multipart;
	}
	
	private static byte[] convertFiletoByteArray(File file){
		
		byte[] byteArray = null ;
		try{
				byteArray = FileUtils.readFileToByteArray(file);
		}catch(Exception e){
			
		}
		return byteArray;
	}
		
}
