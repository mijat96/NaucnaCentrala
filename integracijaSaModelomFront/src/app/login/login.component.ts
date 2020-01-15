import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/loginService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private logService: LoginService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit(value, form){
    let x = this.logService.login(value.username, value.password);

    x.subscribe(
       res => {
         //console.log("odgovor pri logovanju" + res.uloga);
        
         alert("You registered successfully!");

        localStorage.setItem("uloga", res.uloga);
        localStorage.setItem("username", value.username);
        
         this.router.navigate(['home'])

       }
     );
  }

}
