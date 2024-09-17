import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './auth.guard';
import { MySettingsComponent } from './mysettings/mysettings.component';
import { EducationProfileComponent } from './education-profile/education-profile.component';
import { ExperienceProfileComponent } from './experience-profile/experience-profile.component';
import { profile } from 'console';
import {  UserProfileComponent } from './user-profile/user-profile.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent, canActivate: [AuthGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [AuthGuard] },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  {
    path: 'my-settings',
    component: MySettingsComponent,
    canActivate: [AuthGuard],
  },

  {
    path: 'user-profile',
    component: UserProfileComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'experience-profile',
    component: ExperienceProfileComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'education-profile',
    component: EducationProfileComponent,
    canActivate: [AuthGuard],
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/login' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
