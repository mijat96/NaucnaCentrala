package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import root.demo.model.FormSubmissionDto;
import root.demo.model.HashCodeConfirm;
import root.demo.repository.HashCodeRepository;

public class CheckRegistration implements JavaDelegate{

	@Autowired
	HashCodeRepository hashRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("registration");
//		String username = registration.get(6).getFieldValue();
//		boolean potrvdjeno = false;
//		HashCodeConfirm code = new HashCodeConfirm();
//		
//		List<HashCodeConfirm> allHashCode = hashRepository.findAll();
//    	for(HashCodeConfirm c: allHashCode) {
//			if(c.getUsername().equals(username)) {
//				code = c;
//				break;
//			}
//		}
//    	
//    	while(!code.getPotvrdjen().equals("da")) {
//    		List<HashCodeConfirm> HashCode = hashRepository.findAll();
//    		for(HashCodeConfirm c: allHashCode) {
//    			if(c.getUsername().equals(username)) {
//    				code = c;
//    				break;
//    			}
//    		}
//    		Thread.sleep(5000);
//    	}
	}
}

