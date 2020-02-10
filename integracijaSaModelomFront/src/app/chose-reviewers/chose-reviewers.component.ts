import { Component, OnInit } from '@angular/core';
import { TextEditingService } from '../services/textEditingService';
import { Router } from '@angular/router';
import { Magazine } from '../models/magazine';

@Component({
  selector: 'app-chose-reviewers',
  templateUrl: './chose-reviewers.component.html',
  styleUrls: ['./chose-reviewers.component.css']
})
export class ChoseReviewersComponent implements OnInit {

  private uspesno = false;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private enumValues = [];

  private stringMagazines = "";
  private allMagaziens = [];
  private selectedMagazin = "";
  private selectedMagazi1 = "";
  constructor(private textEditingService: TextEditingService,private router: Router) { 
    let x = textEditingService.getReviewChoseTextForm();

    x.subscribe(
      res => {
        console.log(res);
        //this.categories = res;
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        //console.log("*******" + this.processInstance + ", procIns iz odgovora " + res.processInstanceId)
        this.formFields.forEach( (field) =>{
          
          if( field.type.name=='enum'){
            this.enumValues = Object.keys(field.type.values);
          }
          if( field.type.name=='string'){
            this.stringMagazines = field.value.value;
            this.allMagaziens = this.stringMagazines.split('|');
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

  onChangeEditors($event) {
    //console.log(this.selectedMagazin)
    //var m = new Magazine;
    //m.naziv = this.selectedMagazin;
    //let x = this.textEditingService.choseMagazine(this.formFieldsDto.taskId, m);

    // x.subscribe(
    //   res => {
    //     console.log(res);
        
    //     alert("Successs");
    //     this.uspesno = true;
    //     this.router.navigate(['home'])
    //     //this.router.navigate(['textInformation', {processInstance: this.processInstance}]); //RADI REDIREKT SA SLANJEM PARAMETRA     
    //   },
    //   err => {
    //     console.log("Error occured");
    //   }
    // );
  }

  onChangeEditorss($event) {
    //console.log(this.selectedMagazin)
    //var m = new Magazine;
    //m.naziv = this.selectedMagazin;
    //let x = this.textEditingService.choseMagazine(this.formFieldsDto.taskId, m);

    // x.subscribe(
    //   res => {
    //     console.log(res);
        
    //     alert("Successs");
    //     this.uspesno = true;
    //     this.router.navigate(['home'])
    //     //this.router.navigate(['textInformation', {processInstance: this.processInstance}]); //RADI REDIREKT SA SLANJEM PARAMETRA     
    //   },
    //   err => {
    //     console.log("Error occured");
    //   }
    // );
  }

  dodaj(){
    var m = new Magazine;
    console.log(this.selectedMagazi1 + " " + this.selectedMagazin);
    m.recenzent1 = this.selectedMagazi1;
    m.recenzent2 = this.selectedMagazin;
    let x = this.textEditingService.postReviewChoseTextForm(this.formFieldsDto.taskId, m);

    x.subscribe(
      res => {
        console.log(res);
        
        alert("Successs");
        this.uspesno = true;
        this.router.navigate(['home'])
        //this.router.navigate(['textInformation', {processInstance: this.processInstance}]); //RADI REDIREKT SA SLANJEM PARAMETRA     
      },
      err => {
        console.log("Error occured");
      }
    );
  }

}
