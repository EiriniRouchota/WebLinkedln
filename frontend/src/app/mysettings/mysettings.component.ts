import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgModule } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AlertService } from '../services/alert.service'; // Import the AlertService

@Component({
  selector: 'app-mysettings',
  templateUrl: './mysettings.component.html',
  styleUrls: ['./mysettings.component.scss'],
})
export class MySettingsComponent implements OnInit {
  userForm!: FormGroup;
  userProfileSettings: any;
  email: string = '';
  password: string = '';
  newPassword: string = '';
  confirmNewPassword: string = '';
  hashedPassword: string = ''; // Add a property to store the hashed password

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private alertService: AlertService
  ) {}

  ngOnInit(): void {
    this.getUserProfile();
  }

  onSubmit() {
    const token = this.authService.getToken(); // Retrieve the stored token

    if (!token) {
      console.error('No token found, cannot make authenticated request');
      return;
    }

    // Set the Authorization header with the token
    let headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    // Prepare the request body
    let bodyData = {
      email: this.email,
      password: this.password,
      newPassword: this.newPassword,
    };

    // Send the POST request with headers and body data
    this.http
      .post(
        'http://localhost:8080/api/v1/employee/auth/users/me/update',
        bodyData,
        { headers }
      )
      .subscribe({
        next: (data: any) => {
          console.log('User profile updated:', data);
          if (data.message === 'Profile updated successfully.') {
            this.alertService.showAlert(
              'success',
              'Profile updated successfully.'
            );
          } else if (data.message === 'Your password is incorrect') {
            this.alertService.showAlert('danger', 'Your password is incorrect');
          } else {
            data.message ===
              'Email is already in use. Try again with a different email.';
            this.alertService.showAlert(
              'warning',
              'Email is already in use. Try again with a different email.'
            ); // Handle other cases if needed
          }
        },
        error: (error) => {
          console.error('Error updating user profile:', error);
        },
      });
  }

  getUserProfile(): void {
    const token = this.authService.getToken(); // Retrieve the stored token

    if (!token) {
      console.error('No token found, cannot make authenticated request');
      return;
    }

    let headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    console.log(headers);
    this.http
      .get('http://localhost:8080/api/v1/employee/auth/users/me', { headers })
      .subscribe({
        next: (data: any) => {
          console.log('User profile data:', data);
          this.email = data.email;
        },
        error: (error) => {
          console.error('Error fetching user profile:', error);
        },
      });
  }
}
