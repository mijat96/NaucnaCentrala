import { Component, OnInit, Input } from '@angular/core';
import { TextEditingService } from '../services/textEditingService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-text-information',
  templateUrl: './text-information.component.html',
  styleUrls: ['./text-information.component.css']
})
export class TextInformationComponent implements OnInit {
  //@Input() processInstance: string;

  private uspesno = false;
  private formFieldsDto = null;
  private formFields = [];
  private enumValues = [];
  private scientificPaperFile;

  constructor(private textEditingService: TextEditingService, private router: Router) {
    
   
  }

  ngOnInit() {
    
    let x = this.textEditingService.getInformationTextForm();

    x.subscribe(
      res => {
        console.log(res);
        //this.categories = res;
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        //this.processInstance = res.processInstanceId;
        //console.log(this.processInstance + ", procIns iz odgovora " + res.processInstanceId)
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

  onSubmit(value, form){
    let o = new Array();
    for (var property in value) {
      console.log(property);
      console.log(value[property]);
      o.push({fieldId : property, fieldValue : value[property]});
    }

    console.log(o);
    let x = this.textEditingService.postInformationText(this.formFieldsDto.taskId, o);
    //PDF
    //let x = this.textEditingService.sendPaper(this.scientificPaperFile, o);
    
    x.subscribe(
      res => {
        console.log(res);
        
        alert("You add text information successfully!");
        this.uspesno = true;
        this.router.navigate(['masterEditorConfirm']);
        //this.router.navigate(['popunjavanjeOblasti', {processInstance: this.processInstance}]) RADI REDIREKT SA SLANJEM PARAMETRA
        
      },
      err => {
        console.log("Error occured");
      }
    );
  }

  onChange(event) {
    this.scientificPaperFile = event.target.files.item(0);
  }
}
