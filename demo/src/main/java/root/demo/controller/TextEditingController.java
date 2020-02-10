package root.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import root.demo.model.FormFieldsDto;
import root.demo.model.FormSubmissionDto;
import root.demo.model.Magazine;
import root.demo.model.TaskDto;
import root.demo.model.User;
import root.demo.repository.MagazineRepository;

@Controller
@RequestMapping("/textEditing")
public class TextEditingController {
	
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
	MagazineRepository magazineRepository;
	
	@GetMapping(path = "/getFormTextEditingStart", produces = "application/json")
    public @ResponseBody FormFieldsDto get() {
		
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("Process_Obrade_Teksta");

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }
	
	@PostMapping(path = "/postForm/{taskId}", produces = "application/json")
	 public @ResponseBody ResponseEntity post(@RequestBody Magazine magazineName, @PathVariable String taskId) {
		HashMap<String, Object> map = new HashMap();
//				return new ResponseEntity<>(HttpStatus.OK);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		Magazine m = magazineRepository.findByNaziv(magazineName.getNaziv()).get();
		runtimeService.setVariable(processInstanceId, "casopisID", magazineName.getNaziv());
		
		if(m.getNacinPlacanja().equals("korisnik")) {
			runtimeService.setVariable(processInstanceId, "openAccessID", "false");
		}else
		{
			runtimeService.setVariable(processInstanceId, "openAccessID", "true");
		}
		formService.submitTaskForm(taskId, map);
		//System.out.println(magazineName.getNaziv());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path = "/getInformationTextForm", produces = "application/json")
    public @ResponseBody FormFieldsDto getInformationTextForm() {
		
		Task task = taskService.createTaskQuery().taskDefinitionKey("Unos_Informacija_task").singleResult();
		
		String processInstanceId = task.getProcessInstanceId();
//		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
//		Task task = tasks.get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(), processInstanceId, properties);
    }
	
	//Popunjena forma sa informacijama o tekstu
	@PostMapping(path = "/postInformationTextForm/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity postpostFormConfirmReviewer(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "textInformationDto", dto);
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.OK);		
    }
	
	@GetMapping(path = "/getMasterEditorForm", produces = "application/json")
    public @ResponseBody FormFieldsDto getMasterEditorForm() {
		
		Task task = taskService.createTaskQuery().taskDefinitionKey("GlavniUrednik_ProveraTeksta").singleResult();
		
		String processInstanceId = task.getProcessInstanceId();
//		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
//		Task task = tasks.get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(), processInstanceId, properties);
    }
	
	
	//Popunjena forma glavnog urednika o proveri
	@PostMapping(path = "/postMasterEditorCheckForm/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity postMasterEditorCheckForm(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "masterEditorCheck", dto);
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.OK);		
    }	
	
	
//	@RequestMapping(value ="/add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity addScientificPaper(@RequestParam("processInstanceId") String processInstanceId,
//                                            @RequestPart("scientific_paper_file") MultipartFile file,
//                                            @RequestPart("scientific_paper_data") ScientificPaperDTO scientificPaperDTO) {
////        UserData loggedUser = null;
////        try {
////            loggedUser = userService.getLoggedUser();
////        } catch (Exception e) {
////            new ResponseEntity(HttpStatus.UNAUTHORIZED);
////        }
//
//        if (file == null || scientificPaperDTO == null) {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//
//
//        Map<String, Object> formFieldsMap = new HashMap<String, Object>();
//        formFieldsMap.put("title", scientificPaperDTO.getTitle());
//        formFieldsMap.put("coauthors", scientificPaperDTO.getCoauthors());
//        formFieldsMap.put("keywords", scientificPaperDTO.getKeywords());
//        formFieldsMap.put("abstract", scientificPaperDTO.getScientificPaperAbstract());
//        formFieldsMap.put("selectedScientificAreaId", scientificPaperDTO.getScientificAreaId());
//        formFieldsMap.put("fileName", file.getName());
//
//
////        try {
////            scientificPaperService.submitFirstUserTask(loggedUser.getCamundaUserId(), processInstanceId, formFieldsMap);
////        } catch (NotFoundException | TaskNotAssignedToYouException e) {
////            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
////        }
//
//        return new ResponseEntity(HttpStatus.CREATED);
//    }
	
	@GetMapping(path = "/getMasterEditorPDFForm", produces = "application/json")
    public @ResponseBody FormFieldsDto getMasterEditorPDFForm() {
		
		Task task = taskService.createTaskQuery().taskDefinitionKey("Urednik_pregledaPDF").singleResult();
		
		String processInstanceId = task.getProcessInstanceId();
//		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
//		Task task = tasks.get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(), processInstanceId, properties);
    }
	
	//Popunjena forma glavnog urednika o proveri
	@PostMapping(path = "/postMasterEditorCheckPDFForm/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity postMasterEditorChecPDFkForm(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "masterEditorCheckPDF", dto);
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.OK);		
    }
	
	@GetMapping(path = "/getReviewComentAndReturnPostTextForm", produces = "application/json")
    public @ResponseBody FormFieldsDto getReviewComentAndReturnPostTextForm() {
		
		Task task = taskService.createTaskQuery().taskDefinitionKey("Pregled_Komentara_I_Ponovno_Postavljanje_Rada").singleResult();
		
		String processInstanceId = task.getProcessInstanceId();
//		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
//		Task task = tasks.get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(), processInstanceId, properties);
    }
	
	//Popunjena forma glavnog urednika o proveri
	@PostMapping(path = "/postReviewCommentAndReturnTextForm/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity postReviewCommentAndReturnTextForm(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "returnPDF", dto);
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.OK);		
    }
	
	@GetMapping(path = "/getReviewChoseTextForm", produces = "application/json")
    public @ResponseBody FormFieldsDto getReviewChoseTextForm() {
		
		Task task = taskService.createTaskQuery().taskDefinitionKey("Izbor_Recenzenata").singleResult();
		
		String processInstanceId = task.getProcessInstanceId();
//		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
//		Task task = tasks.get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(), processInstanceId, properties);
    }
	
	//Popunjena forma glavnog urednika o proveri
	@PostMapping(path = "/postReviewChoseTextForm/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity postReviewChoseTextForm(@RequestBody Magazine magazineName, @PathVariable String taskId) {
		HashMap<String, Object> map = new HashMap();
		//		return new ResponseEntity<>(HttpStatus.OK);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		//String magazineN = (String)execution.getVariable("casopisID");
		String magazineN = (String)runtimeService.getVariable(processInstanceId, "casopisID");
		
		Magazine m = magazineRepository.findByNaziv(magazineN).get();
		runtimeService.setVariable(processInstanceId, "recenzent1", magazineName.getRecenzent2());
		runtimeService.setVariable(processInstanceId, "recenzent2", magazineName.getRecenzent2());
		long l = 15;
		runtimeService.setVariable(processInstanceId, "vremeRecenzijeID", l);
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
