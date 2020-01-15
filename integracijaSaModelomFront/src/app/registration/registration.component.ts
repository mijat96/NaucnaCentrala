import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../services/registrationService';
import { RepositoryService } from '../services/repositoryService';
import {Router} from "@angular/router"

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  private uspesno = false;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private enumValues = [];

  constructor(private regService: RegistrationService, private repositoryService: RepositoryService, private router: Router) { 
    
    let x = regService.startProcess();

    x.subscribe(
      res => {
        console.log(res);
        //this.categories = res;
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        console.log(this.processInstance + ", procIns iz odgovora " + res.processInstanceId)
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
    let x = this.regService.registerUser(this.formFieldsDto.taskId, o);

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

  exist(){
    //console.log(this.uspesno);
    return this.uspesno;
  }

}
