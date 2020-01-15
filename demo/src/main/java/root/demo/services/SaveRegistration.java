package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.model.FormSubmissionDto;
import root.demo.model.User;
import root.demo.repository.UserRepository;

@Service
public class SaveRegistration implements JavaDelegate{

	@Autowired
	IdentityService identityService;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("registration");
		String ime = registration.get(0).getFieldValue();
		String prezime = registration.get(1).getFieldValue();
		String grad = registration.get(2).getFieldValue();
		String drzava = registration.get(3).getFieldValue();
		String titula = registration.get(4).getFieldValue();
		String email = registration.get(5).getFieldValue();
		String username = registration.get(6).getFieldValue();
		String password = registration.get(7).getFieldValue();
		String recenzent = registration.get(8).getFieldValue();
		String naucneOblasti = registration.get(9).getFieldValue();
		
		User u = new User(ime, prezime, grad, drzava, titula, email, username, password, naucneOblasti);
		
		if(recenzent.equals("true")) {
			u.setUloga("recenzent");
		}else {
			u.setUloga("user");;
		}

		try {
			userRepository.save(u);			
			System.out.println("Uspesno sacuvan");
		}catch(NullPointerException e) {
			System.out.println("Nije sacuvano u bazu");
		}
	}

}
