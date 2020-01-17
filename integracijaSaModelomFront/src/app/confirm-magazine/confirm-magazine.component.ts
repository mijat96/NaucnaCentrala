import { Component, OnInit } from '@angular/core';
import { MagazineService } from '../services/magazineService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-confirm-magazine',
  templateUrl: './confirm-magazine.component.html',
  styleUrls: ['./confirm-magazine.component.css']
})
export class ConfirmMagazineComponent implements OnInit {

  private uspesno = false;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private enumValues = [];

  constructor(private magService: MagazineService, private router: Router) { 
    let x = magService.getConfirmMagazine();

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
      if(property == "ProveraUspesnaID"){
        if(value[property] != true){
          value[property] = "false";
          console.log("Nije potvrdjena registracija " + value[property])
        }
      }
      o.push({fieldId : property, fieldValue : value[property]});
    }

    console.log(o);
    let x = this.magService.putConfirmMagazine(this.formFieldsDto.taskId, o);

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
