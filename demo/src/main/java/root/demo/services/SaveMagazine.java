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
public class SaveMagazine implements JavaDelegate{

	@Autowired
	MagazineRepository magRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("newMagazine");
		String editor = (String)execution.getVariable("newMagazineChiefEditor");
		boolean isUpdated = false;
		//treba samo proveriti da li casopis vec postoji i azurirati ga
		String naziv = registration.get(0).getFieldValue();
		Long issn = Long.parseLong(registration.get(1).getFieldValue());
		String naucnaOblast = registration.get(2).getFieldValue();
		String koPlaca = registration.get(3).getFieldValue();
		
		List<Magazine> allMagazine = magRepository.findAll();			
		
		if(!allMagazine.isEmpty()) {
			for(Magazine curentMagazine: allMagazine) {
				if(curentMagazine.getNaziv().equals(naziv)) {
					curentMagazine.setIssn(issn);
					curentMagazine.setNacinPlacanja(koPlaca);
					curentMagazine.setNaucneOblasti(naucnaOblast);
					magRepository.save(curentMagazine);
					isUpdated = true;
					break;
				}
			}
		}
		
		if(!isUpdated) {
			Magazine m = new Magazine(naziv, issn, naucnaOblast, koPlaca);
			m.setAktivan("ne");
			m.setGlavniUrednik(editor);
			
			magRepository.save(m);			
		}
	}

}
