import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TextEditingService {

  categories = [];
  languages = [];
  books = [];

  constructor(private httpClient: HttpClient) { 
    

  }

  startProcess(){
    return this.httpClient.get('http://localhost:8082/textEditing/getFormTextEditingStart') as Observable<any>
  }

  choseMagazine(taskId, magazine){
    console.log(magazine + " Magazin")
    return this.httpClient.post('http://localhost:8082/textEditing/postForm/'.concat(taskId), magazine) as Observable<any>
  }

  getInformationTextForm(){
    return this.httpClient.get('http://localhost:8082/textEditing/getInformationTextForm') as Observable<any>
  }

  postInformationText(taskId, user){
    return this.httpClient.post('http://localhost:8082/textEditing/postInformationTextForm/'.concat(taskId), user) as Observable<any>
  }

  getMasterEditorForm(){
    return this.httpClient.get('http://localhost:8082/textEditing/getMasterEditorForm') as Observable<any>
  }

  postMasterEditorCheck(taskId, user){
    return this.httpClient.post('http://localhost:8082/textEditing/postMasterEditorCheckForm/'.concat(taskId), user) as Observable<any>
  }

  sendPaper(file: any, data: any) {
    const fd = new FormData();
    fd.append('scientific_paper_file', file);
    fd.append('scientific_paper_data', new Blob([JSON.stringify(data)], {type: 'application/json'}));
    return this.httpClient.post('http://localhost:8082/textEditing/postPdf/', fd);
  }

  getMasterEditorPDFForm(){
    return this.httpClient.get('http://localhost:8082/textEditing/getMasterEditorPDFForm') as Observable<any>
  }

  postMasterEditorPDFCheck(taskId, user){
    return this.httpClient.post('http://localhost:8082/textEditing/postMasterEditorCheckPDFForm/'.concat(taskId), user) as Observable<any>
  }

  getReviewComentAndReturnPostTextForm(){
    return this.httpClient.get('http://localhost:8082/textEditing/getReviewComentAndReturnPostTextForm') as Observable<any>
  }

  postReviewCommentAndReturnTextForm(taskId, user){
    return this.httpClient.post('http://localhost:8082/textEditing/postReviewCommentAndReturnTextForm/'.concat(taskId), user) as Observable<any>
  }

  getReviewChoseTextForm(){
    return this.httpClient.get('http://localhost:8082/textEditing/getReviewChoseTextForm') as Observable<any>
  }

  postReviewChoseTextForm(taskId, user){
    return this.httpClient.post('http://localhost:8082/textEditing/postReviewChoseTextForm/'.concat(taskId), user) as Observable<any>
  }

//   registerUser(taskId, user){
//     return this.httpClient.post('http://localhost:8082/registration/postForm/'.concat(taskId), user) as Observable<any>
//   }

  // oblasti(procesInstance: string){
  //   console.log("dobavaljanje forme za oblasti, proc " + procesInstance);
  //   return this.httpClient.get('http://localhost:8082/registration/getFormOblasti/' + procesInstance) as Observable<any>
  // }
  

//   getReviewerConfirmForm(){
//     return this.httpClient.get('http://localhost:8082/registration/getReviewerConfirmForm') as Observable<any>
//   }

//   putConfirmReviewer(taskId, user){
//     return this.httpClient.post('http://localhost:8082/registration/postFormConfirmReviewer/'.concat(taskId), user) as Observable<any>
//   }

}