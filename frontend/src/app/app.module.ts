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

import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { SkillsComponent } from './skills/skills.component';
// Import Angular Material modules
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // Required for animations in Material

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
    MatSelectModule, // For <mat-select>
    MatButtonModule, // For <mat-button>
  ],
  providers: [provideAnimationsAsync()],
  bootstrap: [AppComponent],
})
export class AppModule {}
