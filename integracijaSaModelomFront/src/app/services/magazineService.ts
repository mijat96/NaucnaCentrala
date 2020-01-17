import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MagazineService {

  constructor(private httpClient: HttpClient) { 
    

  }

  getMagazineForm(pInstance){
    if(pInstance != undefined){
      return this.httpClient.get('http://localhost:8082/newMagazine/getFormNewMagazine/' + pInstance) as Observable<any>
    }
    else{
      return this.httpClient.get('http://localhost:8082/newMagazine/getFormNewMagazine') as Observable<any>
    }
  }

  newMagazine(taskId, magazine, username){
    return this.httpClient.post('http://localhost:8082/newMagazine/postForm/' + taskId + '/' + username, magazine) as Observable<any>
  }

  getReviewerAndEditororm(){
    return this.httpClient.get('http://localhost:8082/newMagazine/getReviewerAndEditorForm') as Observable<any>
  }

  putReviewerAndEditorToMAgazine(taskId, user){
    return this.httpClient.post('http://localhost:8082/newMagazine/postFormConfirmEditorAndReviewer/'.concat(taskId), user) as Observable<any>
  }

  getConfirmMagazine(){
    return this.httpClient.get('http://localhost:8082/newMagazine/getMagazineConfirmForm') as Observable<any>
  }

  putConfirmMagazine(taskId, user){
    return this.httpClient.post('http://localhost:8082/newMagazine/postFormConfirmMagazine/'.concat(taskId), user) as Observable<any>
  }

}