import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router) { }

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

  daLiJeUlogovan(){
    if(localStorage.uloga == undefined || localStorage.uloga == "undefined"){
      console.log("Lose");
      return true;
     }
     else{
      console.log("Dobro");
       return false;
     }
  }


  logout(){
    localStorage.uloga = undefined;
    this.router.navigate(['home']);
  }
}
