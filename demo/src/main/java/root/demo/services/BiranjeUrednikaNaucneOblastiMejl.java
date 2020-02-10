package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.model.Magazine;
import root.demo.model.User;
import root.demo.repository.UserRepository;

@Service
public class BiranjeUrednikaNaucneOblastiMejl implements JavaDelegate{

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String processInstanceId = execution.getId();
		
		//List<Magazine> allMagazines = magazineRepository.findAll();
		List<User> allUsers = userRepository.findAll();
		String allUsersStrings = "";
		
		for(User u: allUsers) {
			allUsersStrings += u.getIme() + "|";
		}
		
		runtimeService.setVariable(processInstanceId, "allReviewesFromBase", allUsersStrings);
	}

}
