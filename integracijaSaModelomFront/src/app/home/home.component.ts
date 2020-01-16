import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  daLiJeUrednik(){
    if(localStorage.uloga == "urednik"){
      return true;
     }
     else{
       return false;
     }
  }

  daLiJeAdmin(){
    if(localStorage.uloga == "admin"){
      return true;
     }
     else{
       return false;
     }
  }

}
