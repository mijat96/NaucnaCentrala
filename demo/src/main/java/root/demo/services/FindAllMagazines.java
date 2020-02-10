package root.demo.services;

import java.io.Console;
import java.util.List;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.model.Magazine;
import root.demo.model.User;
import root.demo.repository.MagazineRepository;

@Service
public class FindAllMagazines implements JavaDelegate{

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	MagazineRepository magazineRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String processInstanceId = execution.getId();
		
		List<Magazine> allMagazines = magazineRepository.findAll();
		String allMagazineNames = "";
		
		for(Magazine m: allMagazines) {
			allMagazineNames += m.getNaziv() + "|";
		}
		
		runtimeService.setVariable(processInstanceId, "allMagazinesFromBase", allMagazineNames);
	}

}
