package root.demo.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class SendMailNewText implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String magazineName = (String)execution.getVariable("casopisID");
		
		send("sep.ftn.2019@gmail.com","placanje123", "marko_srb@hotmail.rs","Novi tekst","Podnet je novi tekst za magazin " + magazineName);
	}

	public void send(String from,String password,String to,String sub,String msg){  
        //Get properties object    
        Properties props = new Properties();    
	        props.put("mail.smtp.host", "smtp.gmail.com");    
	        props.put("mail.smtp.socketFactory.port", "465");    
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");    
	        props.put("mail.smtp.auth", "true");    
	        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(from,password);  
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
	         message.setSubject(sub);    
	         message.setText(msg);    
         //send message  
         Transport.send(message);    
         System.out.println("message sent successfully");    
        } catch (MessagingException e) {throw new RuntimeException(e);}            
	}  
}
