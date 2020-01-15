package root.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.externaltask.LockedExternalTask;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import root.demo.model.HashCodeConfirm;
import root.demo.model.LoginResponse;
import root.demo.model.User;
import root.demo.repository.HashCodeRepository;
import root.demo.repository.UserRepository;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

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
	
	@Autowired
	HashCodeRepository codeRepository;
	
	@Autowired
	ExternalTaskService externalTaskService;
	
	//Startovanje procesa registracije i vracanje forme klijentu
	@GetMapping(path = "/getFormRegistration", produces = "application/json")
    public @ResponseBody FormFieldsDto get() {
		
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("registracija");

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }
	
	//Cuvanje popunjene forme registracije
	@PostMapping(path = "/postForm/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity post(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		
		boolean exist = false;

		List<User> allUsers = userRepository.findAll();
		
		if(!allUsers.isEmpty()) {
			for(User uu: allUsers) {
				if(uu.getUsername() == dto.get(7).getFieldValue()) {
					System.out.println("Korisnik vec postoji sa tim username");
					exist = true;
				}
			}
		}
		
		if(!exist) {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			String processInstanceId = task.getProcessInstanceId();
			runtimeService.setVariable(processInstanceId, "registration", dto);
			formService.submitTaskForm(taskId, map);
			return new ResponseEntity<>(HttpStatus.OK);			
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    }
	
	//Ne koristi se vise, forma za oblasti je u formi registracije
	@GetMapping(path = "/getFormOblasti/{procesInstance}", produces = "application/json")
    public @ResponseBody FormFieldsDto getOblasti(String procesInstance) {
		//provera da li korisnik sa id-jem pera postoji
		//List<User> users = identityService.createUserQuery().userId("pera").list();
		//ProcessInstance pi = runtimeService.startProcessInstanceByKey("registracija");
		System.out.println(procesInstance);
		Task task = taskService.createTaskQuery().processInstanceId(procesInstance).list().get(0);
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		/*
		for(FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}
		*/
		
        return new FormFieldsDto(task.getId(), procesInstance, properties);
    }
	
	//Provera hash vrednosti poslete na mejl i potrvrda registracije
	@GetMapping(path = "/checkHash/{sha256hex}/{username}", produces = "application/json")
    public @ResponseBody ResponseEntity getHash(@PathVariable String sha256hex, @PathVariable String username) {
		
		System.out.println(sha256hex);
		
		List<HashCodeConfirm> allHashCode = codeRepository.findAll();
		
		for(HashCodeConfirm c: allHashCode) {
			if(c.getUsername().equals(username) && c.getHashCode().equals(sha256hex)) {
				c.setPotvrdjen("da");
				codeRepository.save(c);
				break;
			}
		}
		
		
		List<LockedExternalTask> tasks = externalTaskService.fetchAndLock(1, "externalWorkerId")
				  .topic("EmailValidation", 60L * 1000L)
				  .execute();

				for (LockedExternalTask task : tasks) {
				  try {
				    String topic = task.getTopicName();
				    
				      externalTaskService.complete(task.getId(), "externalWorkerId");
				  }
				  catch(Exception e) {

					  System.out.println("External task nije uspesno zavrsen!");
				  }
				}
				String content = 
				           "<header>"
				         + "<h1>Uspesno ste potvrdili registraciju</h1>"
				         + "</header>";
				    HttpHeaders responseHeaders = new HttpHeaders();
				    responseHeaders.setContentType(MediaType.TEXT_HTML);
				    
		return new ResponseEntity<String>(content, responseHeaders, HttpStatus.OK);
    }
	
	//Slanje forme adminu za potrvrdu rezenzenta 
	@GetMapping(path = "/getReviewerConfirmForm", produces = "application/json")
    public @ResponseBody FormFieldsDto getReviewerConfirmForm() {
		
		Task task = taskService.createTaskQuery().taskDefinitionKey("Provera_Recenzenta").singleResult();
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(), "", properties);
    }
	
	//Popunjena forma za potvrdu recenzenta
	@PostMapping(path = "/postFormConfirmReviewer/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity postpostFormConfirmReviewer(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "checkReviewer", dto);
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.OK);		
    }
	
	//Login
	@GetMapping(path = "/login/{username}/{password}", produces = "application/json")
    public @ResponseBody ResponseEntity getLogin(@PathVariable String username, @PathVariable String password) {
		List<User> allUsers = userRepository.findAll();
		String uloga = "";
		boolean pronadjen = false;
		
		for(User u: allUsers) {
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				pronadjen = true;
				switch(u.getUloga()) {
				  case "admin":
				    uloga = "admin";
				    break;
				  case "user":
				    uloga = "user";
				    break;
				  case "recenzent":
					    uloga = "recenzent";
					    break;
				  case "urednik":
					    uloga = "urednik";
					    break;
				  default:
				    uloga = "user";
				}
			}
		}
		
		if(pronadjen) {
			System.out.println("Uspesno logovanje");
			return new ResponseEntity<LoginResponse>(new LoginResponse(uloga, "Uspesno ste se ulogovali"), HttpStatus.OK);		
		}else
		{
			System.out.println("Neuspesno logovanje");
			return new ResponseEntity<>("neusepesno logovanje", HttpStatus.OK);
		}
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
