import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TextEditingService } from '../services/textEditingService';

@Component({
  selector: 'app-review-comment-and-return-post-text',
  templateUrl: './review-comment-and-return-post-text.component.html',
  styleUrls: ['./review-comment-and-return-post-text.component.css']
})
export class ReviewCommentAndReturnPostTextComponent implements OnInit {

  private uspesno = false;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private enumValues = [];

  constructor(private textEditingService: TextEditingService, private router: Router) {
    let x = textEditingService.getReviewComentAndReturnPostTextForm();

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
    for (var property in value) {
      console.log(property);
      console.log(value[property]);
      o.push({fieldId : property, fieldValue : value[property]});
    }

    console.log(o);
    let x = this.textEditingService.postReviewCommentAndReturnTextForm(this.formFieldsDto.taskId, o);
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

}
