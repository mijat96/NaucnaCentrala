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
public class UpdateMagazine implements JavaDelegate{

	@Autowired
	MagazineRepository magRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("AddEditorAndReviewer");
		List<FormSubmissionDto> registration1 = (List<FormSubmissionDto>)execution.getVariable("newMagazine");
		
		String recenzent1 = registration.get(0).getFieldValue();
		String recenzent2 = registration.get(1).getFieldValue();
		String urednik1 = registration.get(2).getFieldValue();
		String urednik2 = registration.get(3).getFieldValue();
		
		String naziv = registration1.get(0).getFieldValue();
		
		List<Magazine> allMagazine = magRepository.findAll();
		
		for(Magazine m: allMagazine) {
			if(m.getNaziv().equals(naziv)) {
				m.setRecenzent1(recenzent1);
				m.setRecenzent2(recenzent2);
				m.setUrednik1(urednik1);
				m.setUrednik2(urednik2);
				magRepository.save(m);
				break;
			}
		}
		
		
	}

}
