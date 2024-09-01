import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '../services/alert.service'; // Import the AlertService
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'], // Note the correct plural form 'styleUrls'
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(
    private router: Router,
    private http: HttpClient,
    private alertService: AlertService,
    private authService: AuthService // Inject the AuthService
  ) {}

  Login(loginForm: NgForm) {
    // Check if the form is valid
    if (!loginForm.valid) {
      return;
    }

    let bodyData = {
      email: this.email,
      password: this.password,
    };

    this.http
      .post('http://localhost:8080/api/v1/employee/login', bodyData)
      .subscribe({
        next: (resultData: any) => {
          console.log(resultData);

          if (resultData.message === 'Email not exists') {
            this.alertService.showAlert('warning', 'Email does not exist.');
          } else if (resultData.message === 'Login Success') {
            this.authService.setToken(resultData.token, resultData.expiresIn); // Store the token
            this.router.navigateByUrl('/home');
          } else {
            this.alertService.showAlert(
              'danger',
              'Incorrect Email and Password do not match.'
            );
          }
        },
        error: (error) => {
          console.error('There was an error during the login request', error);
          this.alertService.showAlert('danger', 'Wrong email or password');
        },
      });
  }
}
