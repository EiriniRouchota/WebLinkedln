import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { AlertComponent } from './alert/alert.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MySettingsComponent } from './mysettings/mysettings.component';
import { EducationProfileComponent } from './education-profile/education-profile.component';
import { ExperienceProfileComponent } from './experience-profile/experience-profile.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { SkillsComponent } from './skills/skills.component';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    AlertComponent,
    NavbarComponent,
    MySettingsComponent,
    EducationProfileComponent,
    ExperienceProfileComponent,
    UserProfileComponent,
    SkillsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule, // Angular Material requires animations
    MatFormFieldModule, // For <mat-form-field>
    MatSelectModule,
    CommonModule, // <-- Ensure CommonModule is imported here
    FormsModule, // Other modules if needed // For <mat-select>
  ],

  bootstrap: [AppComponent],
})
export class AppModule {}
