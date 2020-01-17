package root.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import root.demo.model.FormFieldsDto;
import root.demo.model.FormSubmissionDto;
import root.demo.model.User;
import root.demo.repository.UserRepository;

@Controller
@RequestMapping("/newMagazine")
public class NewMagazineController {

	@Autowired
	IdentityService identityService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	@Autowired
	UserRepository userRepository;
	
	//Pokretanje novog procesa i forma za novi magazin
	@GetMapping(value = {"/getFormNewMagazine", "/getFormNewMagazine/{pInstance}"}, produces = "application/json")
    public @ResponseBody FormFieldsDto get(@PathVariable(name = "pInstance", required = false) String pInstance) {
		
		//runtimeService.getVariable(executionId, "newMagazine");
		if(pInstance == null) {
			ProcessInstance pi = runtimeService.startProcessInstanceByKey("novi_casopis");
			
			Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
			
			TaskFormData tfd = formService.getTaskFormData(task.getId());
			List<FormField> properties = tfd.getFormFields();
			
			return new FormFieldsDto(task.getId(), pi.getId(), properties);			
		}
		else {
			List<FormSubmissionDto> registration = (List<FormSubmissionDto>)runtimeService.getVariable(pInstance, "newMagazine");
			Task task = taskService.createTaskQuery().processInstanceId(pInstance).list().get(0);
			
			TaskFormData tfd = formService.getTaskFormData(task.getId());
			List<FormField> properties = tfd.getFormFields();
			
			return new FormFieldsDto(task.getId(), pInstance, properties);
		}
	}
	
	//Cuvanje popunjene forme magazina
	@PostMapping(path = "/postForm/{taskId}/{username}", produces = "application/json")
    public @ResponseBody ResponseEntity post(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId, @PathVariable String username) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "newMagazine", dto);
		runtimeService.setVariable(processInstanceId, "newMagazineChiefEditor", username);
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.OK);			
		
    }
	
	//Slanje forme za dodavanje rezenzenta i urednika 
	@GetMapping(path = "/getReviewerAndEditorForm", produces = "application/json")
    public @ResponseBody FormFieldsDto getReviewerConfirmForm() {
		
		Task task = taskService.createTaskQuery().taskDefinitionKey("Dodavanje_recenzenata_task").singleResult();
		
		String processInstanceId = task.getProcessInstanceId();
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(), processInstanceId, properties);
    }
	
	//Popunjena forma za dodavanje rezenzenta i urednika
	@PostMapping(path = "/postFormConfirmEditorAndReviewer/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity postpostFormConfirmReviewer(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "AddEditorAndReviewer", dto);
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.OK);		
    }
	
	//Slanje forme adminu za potrvrdu magazina 
	@GetMapping(path = "/getMagazineConfirmForm", produces = "application/json")
    public @ResponseBody FormFieldsDto getMagazineConfirmForm() {
		
		Task task = taskService.createTaskQuery().taskDefinitionKey("Administrator_proverava").singleResult();
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(), "", properties);
    }
	
	//Popunjena forma za potvrdu magzine
	@PostMapping(path = "/postFormConfirmMagazine/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity postFormConfirmMagazine(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "checkMagazine", dto);
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.OK);		
    }
		
	private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FormSubmissionDto temp : list){
			map.put(temp.getFieldId(), temp.getFieldValue());
		}
		
		return map;
	}
}
