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
  hashedPassword: string = '';
  employeename: string = '';
  employeelastname: string = '';
  selectedFile: File | null = null;
  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private alertService: AlertService
  ) {}

  ngOnInit(): void {
    this.getUserProfile();
  }
  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }
  onSubmit() {
    // Check if the new passwords match
    if (this.confirmNewPassword !== this.newPassword) {
      this.alertService.showAlert(
        'warning',
        'New Password and confirm password must match'
      );
      return;
    }

    const token = this.authService.getToken(); // Retrieve the stored token

    if (!token) {
      console.error('No token found, cannot make authenticated request');
      return;
    }

    // Prepare the FormData object to send file and form data
    const formData = new FormData();

    // Append text fields as part of the form data
    formData.append(
      'employee',
      JSON.stringify({
        email: this.email,
        password: this.password,
        newPassword: this.newPassword,
        employeename: this.employeename,
        employeelastname: this.employeelastname,
      })
    );

    // Append the selected file (photo) if it exists
    if (this.selectedFile) {
      formData.append('photo', this.selectedFile);
    }

    // Set the Authorization header with the token
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    // Send the POST request with headers and FormData
    this.http
      .post(
        'http://localhost:8080/api/v1/employee/auth/users/me/update',
        formData,
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
          } else if (
            data.message ===
            'Email is already in use. Try again with a different email.'
          ) {
            this.alertService.showAlert(
              'warning',
              'Email is already in use. Try again with a different email.'
            );
          } else {
            this.alertService.showAlert('danger', 'An error occurred.');
          }
        },
        error: (error) => {
          console.error('Error updating user profile:', error);
          this.alertService.showAlert('danger', 'Error updating profile.');
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
          this.employeename = data.employeename;
          this.employeelastname = data.employeelastname;
        },
        error: (error) => {
          console.error('Error fetching user profile:', error);
        },
      });
  }
}
