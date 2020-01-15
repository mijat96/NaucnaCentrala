package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.model.FormSubmissionDto;
import root.demo.model.Magazine;
import root.demo.repository.MagazineRepository;

@Service
public class ActivateMagazine implements JavaDelegate{

	@Autowired
	MagazineRepository magRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("checkMagazine");
		String naziv = registration.get(0).getFieldValue();
		String potvrdjen = registration.get(3).getFieldValue();
		
		List<Magazine> allMagazine = magRepository.findAll();
		
		for(Magazine m: allMagazine) {
			if(m.getNaziv().equals(naziv)) {
				if(potvrdjen.equals("true")) {
					m.setAktivan("da");
					break;
				}
			}
		}
	}

}
