import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { AuthService } from '../services/auth/auth.service';
import { AlertService } from '../services/alert.service';

@Component({
  selector: 'app-createads',
  templateUrl: './createads.component.html',
  styleUrl: './createads.component.scss',
})
export class CreateadsComponent {
  jobAdForm: FormGroup | undefined;
  jobAd = {
    companyname: '',
    description: '',
    postedDate: new Date().toISOString(),
    isRemote: '',
    isFulltime: '',
    status: false,
  };
  jobAdFormExists = false;

  addJobAdForm() {
    this.jobAdFormExists = true;
  }

  constructor(
    private authService: AuthService,
    private alertService: AlertService,
    private http: HttpClient
  ) {}

  onSubmitJobAd(form: NgForm) {
    const token = this.authService.getToken(); // Retrieve the stored token

    // Validate that all fields in the form are filled
    if (
      !this.jobAd.companyname ||
      !this.jobAd.description ||
      !this.jobAd.postedDate
    ) {
      console.error('Please fill out all required fields.');
      return;
    }
    // Payload to be sent in the POST request
    const payload = {
      companyname: this.jobAd.companyname,
      description: this.jobAd.description,
      postedDate: this.jobAd.postedDate,
      isRemote: this.jobAd.isRemote,
      isFulltime: this.jobAd.isFulltime,
      status: this.jobAd.status,
    };

    // Set the Authorization header with the token
    let headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    // Submit the job ad form data
    this.http
      .post('http://localhost:8080/api/v1/employee/auth/add/ads', payload, {
        headers,
      })
      .subscribe(
        (response: any) => {
          console.log('Job Ad posted successfully', response);
          this.alertService.showAlert('success', 'Job Ad posted successfully');
          // Reset form and jobAd object after successful submission
          form.resetForm(); // Reset the form
        },
        (error: any) => {
          console.error('Error posting job ad', error);
          this.alertService.showAlert('warning', 'Error posting job ad');
        }
      );
  }
}
