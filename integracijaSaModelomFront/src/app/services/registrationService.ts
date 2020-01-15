import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  categories = [];
  languages = [];
  books = [];

  constructor(private httpClient: HttpClient) { 
    

  }

  startProcess(){
    return this.httpClient.get('http://localhost:8082/registration/getFormRegistration') as Observable<any>
  }

  registerUser(taskId, user){
    return this.httpClient.post('http://localhost:8082/registration/postForm/'.concat(taskId), user) as Observable<any>
  }

  // oblasti(procesInstance: string){
  //   console.log("dobavaljanje forme za oblasti, proc " + procesInstance);
  //   return this.httpClient.get('http://localhost:8082/registration/getFormOblasti/' + procesInstance) as Observable<any>
  // }
  

  getReviewerConfirmForm(){
    return this.httpClient.get('http://localhost:8082/registration/getReviewerConfirmForm') as Observable<any>
  }

  putConfirmReviewer(taskId, user){
    return this.httpClient.post('http://localhost:8082/registration/postFormConfirmReviewer/'.concat(taskId), user) as Observable<any>
  }

}