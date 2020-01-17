import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationService } from '../services/registrationService';
import { MagazineService } from '../services/magazineService';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-new-magazine',
  templateUrl: './new-magazine.component.html',
  styleUrls: ['./new-magazine.component.css']
})
export class NewMagazineComponent implements OnInit {
  
  private uspesno = false;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private enumValues = [];

  constructor(private magService: MagazineService, private router: Router) { 
    
    let x = magService.getMagazineForm(localStorage.processInstance);

    x.subscribe(
      res => {
        console.log(res);
        //this.categories = res;
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        localStorage.setItem("processInstance", this.processInstance);
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
    console.log("Popunjena forma: " + value);
    for (var property in value) {
      console.log(property);
      console.log(value[property]);
      if(property == "field.id"){
        o.push({fieldId : "KoPlacaID", fieldValue : value[property]});
      }else{
        o.push({fieldId : property, fieldValue : value[property]});
      }
    }

    //o.push();

    console.log(o);
    let x = this.magService.newMagazine(this.formFieldsDto.taskId, o, localStorage.username);

    x.subscribe(
      res => {
        console.log(res);
        
        alert("New magazine successfully added!");
        this.uspesno = true;
        this.router.navigate(['addReviewersAndEditorsForMagazine']);
        
      },
      err => {
        console.log("Error occured");
      }
    );
  }

}
