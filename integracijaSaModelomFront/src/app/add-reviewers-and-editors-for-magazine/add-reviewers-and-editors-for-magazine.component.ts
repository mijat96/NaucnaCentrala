import { Component, OnInit } from '@angular/core';
import { MagazineService } from '../services/magazineService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-reviewers-and-editors-for-magazine',
  templateUrl: './add-reviewers-and-editors-for-magazine.component.html',
  styleUrls: ['./add-reviewers-and-editors-for-magazine.component.css']
})
export class AddReviewersAndEditorsForMagazineComponent implements OnInit {

  private uspesno = false;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private enumValues = [];

  constructor(private magService: MagazineService,  private router: Router) {
    let x = magService.getReviewerAndEditororm();

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
    let x = this.magService.putReviewerAndEditorToMAgazine(this.formFieldsDto.taskId, o);

    x.subscribe(
      res => {
        console.log(res);
        
        alert("You registered successfully!");
        this.uspesno = true;
        this.router.navigate(['home']);
        //this.router.navigate(['popunjavanjeOblasti', {processInstance: this.processInstance}]) RADI REDIREKT SA SLANJEM PARAMETRA
        
      },
      err => {
        console.log("Error occured");
      }
    );
  }

}
