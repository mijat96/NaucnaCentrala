import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { Notauthorized } from './guard/notauthorized';
import { FormsModule }   from '@angular/forms';
import { RegistrationService } from './services/registrationService';
import { RepositoryService } from './services/repositoryService';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ConfirmReviewerComponent } from './confirm-reviewer/confirm-reviewer.component';
import { Admin } from './guard/admin.guard';
import { Editor } from './guard/editor.guard';
import { NewMagazineComponent } from './new-magazine/new-magazine.component';
import { AddReviewersAndEditorsForMagazineComponent } from './add-reviewers-and-editors-for-magazine/add-reviewers-and-editors-for-magazine.component';
import { ConfirmMagazineComponent } from './confirm-magazine/confirm-magazine.component';

const routes: Routes = [
  {
    path:"",
    component: HomeComponent,
    canActivate: [Notauthorized]
  },
  {
    path:"home",
    component: HomeComponent,
    canActivate: [Notauthorized]
  },
  {
    path: "registrate",
    component: RegistrationComponent,
    canActivate: [Notauthorized]
  },
  {
    path: "confirmReviewer",
    component: ConfirmReviewerComponent,
    canActivate: [Admin]
  },
  {
    path: "login",
    component: LoginComponent,
    canActivate: [Notauthorized]
  },
  {
    path: "newMagazine",
    component: NewMagazineComponent,
    canActivate: [Editor]
  },
  {
    path: "addReviewersAndEditorsForMagazine",
    component: AddReviewersAndEditorsForMagazineComponent,
    canActivate: [Editor]
  },
  {
    path: "confirmMagazine",
    component: ConfirmMagazineComponent,
    canActivate: [Admin]
  }
]

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    HomeComponent,
    LoginComponent,
    ConfirmReviewerComponent,
    NewMagazineComponent,
    AddReviewersAndEditorsForMagazineComponent,
    ConfirmMagazineComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule
  ],
  providers: [Notauthorized, Admin, Editor],
  bootstrap: [AppComponent]
})
export class AppModule { }
