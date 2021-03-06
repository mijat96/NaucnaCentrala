import { Component, OnInit } from '@angular/core';
import { TextEditingService } from '../services/textEditingService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-master-editor-check',
  templateUrl: './master-editor-check.component.html',
  styleUrls: ['./master-editor-check.component.css']
})
export class MasterEditorCheckComponent implements OnInit {

  private uspesno = false;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private enumValues = [];

  constructor(private textEditingService: TextEditingService, private router: Router) { 
    let x = textEditingService.getMasterEditorForm();

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
      if(property == "relevantanID"){
        if(value[property] != true){
          value[property] = "false";
          console.log("Nije potvrdjena registracija " + value[property])
        }
      }
      o.push({fieldId : property, fieldValue : value[property]});
    }

    console.log(o);
    let x = this.textEditingService.postMasterEditorCheck(this.formFieldsDto.taskId, o);

    x.subscribe(
      res => {
        console.log(res);
        
        alert("You check text successfully!");
        this.uspesno = true;
        this.router.navigate(['pdfReview'])
        //this.router.navigate(['popunjavanjeOblasti', {processInstance: this.processInstance}]) RADI REDIREKT SA SLANJEM PARAMETRA
        
      },
      err => {
        console.log("Error occured");
      }
    );
  }

}
