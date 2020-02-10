package root.demo.services;

import java.util.List;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.model.FormSubmissionDto;
import root.demo.model.Magazine;
import root.demo.model.Text;
import root.demo.repository.MagazineRepository;
import root.demo.repository.TextRepository;

@Service
public class ChoseMasterEditor implements JavaDelegate{

	@Autowired
	MagazineRepository magazineRepository;
	
	@Autowired
	TextRepository textRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String magazineName = (String)execution.getVariable("casopisID");
		List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("textInformationDto");
		
		Magazine m = magazineRepository.findByNaziv(magazineName).get();
		
		Text newText = new Text();
		
		newText.setAktivan("da");
		newText.setGlavniUrednik(m.getGlavniUrednik());
		newText.setMagazine(m.getNaziv());
		newText.setNaslov(registration.get(0).getFieldValue());
		newText.setNaucnaOblast(registration.get(1).getFieldValue());
		newText.setTekst("Tekst rada za magazin bice prilozen u pdf-u");
		
		textRepository.save(newText);
		
		//send("sep.ftn.2019@gmail.com","placanje123", "marko_srb@hotmail.rs","Novi tekst","Podnet je novi tekst za magazin " + magazineName);
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
