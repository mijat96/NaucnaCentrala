package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.model.FormSubmissionDto;
import root.demo.model.Text;
import root.demo.repository.TextRepository;

@Service
public class RefuseText implements JavaDelegate{

	@Autowired
	TextRepository textRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("textInformationDto");
		
		Text t = textRepository.findByNaslov(registration.get(0).getFieldValue()).get();
		
		t.setAktivan("ne");
		textRepository.save(t);
	}

}
