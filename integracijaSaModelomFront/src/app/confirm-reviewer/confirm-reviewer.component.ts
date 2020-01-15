import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../services/registrationService';
import { RepositoryService } from '../services/repositoryService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-confirm-reviewer',
  templateUrl: './confirm-reviewer.component.html',
  styleUrls: ['./confirm-reviewer.component.css']
})
export class ConfirmReviewerComponent implements OnInit {

  private uspesno = false;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private enumValues = [];
  
  constructor(private regService: RegistrationService, private repositoryService: RepositoryService, private router: Router) {
    let x = regService.getReviewerConfirmForm();

    x.subscribe(
      res => {
        console.log(res);
        //this.categories = res;
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        console.log(this.formFieldsDto.taskId + " task id")
        this.formFields.forEach( (field) =>{
          
          if( field.type.name=='enum'){
            this.enumValues = Object.keys(field.type.values);
          }
        });
      },
      err => {
        console.log("Error occured");
      }
    );
  }

  ngOnInit() {
  }

  onSubmit(value, form){
    let o = new Array();
    for (var property in value) {
      console.log(property);
      console.log(value[property]);
      o.push({fieldId : property, fieldValue : value[property]});
    }

    console.log(o);
    let x = this.regService.putConfirmReviewer(this.formFieldsDto.taskId, o);

    x.subscribe(
      res => {
        console.log(res);
        
        alert("You registered successfully!");
        this.uspesno = true;
        this.router.navigate(['home'])
        //this.router.navigate(['popunjavanjeOblasti', {processInstance: this.processInstance}]) RADI REDIREKT SA SLANJEM PARAMETRA
        
      },
      err => {
        console.log("Error occured");
      }
    );
  }

}
