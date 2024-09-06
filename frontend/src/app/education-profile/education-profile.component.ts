import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-education-profile',
  templateUrl: './education-profile.component.html',
})
export class EducationProfileComponent implements OnInit {
  // Declare an array of education forms with default values
  educationForms = [
    {
      degree: '',
      institutionId: '',
      startDate: '',
      endDate: '',
      isPublic: false,
    },
  ];

  // List of institutions
  institutions: any[] = [];

  constructor(private http: HttpClient, private authService: AuthService) {}

  ngOnInit(): void {
    this.educationForms = [];
    this.fetchInstitutions();
    this.fetchUserEducations();
  }

  fetchInstitutions(): void {
    this.http
      .get<any[]>('http://localhost:8080/api/institutions/all')
      .subscribe(
        (data: any) => {
          this.institutions = data;
          console.log('Institutions:', data);
        },
        (error) => {
          console.error('Error fetching institutions', error);
        }
      );
  }

  fetchUserEducations(): void {
    const token = this.authService.getToken(); // Retrieve the stored token

    if (!token) {
      console.error('No token found, cannot make authenticated request');
      return;
    }

    let headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    // Fetch existing educations for the current user
    this.http
      .get<any[]>('http://localhost:8080/api/v1/employee/auth/me/educations', {
        headers,
      })
      .subscribe(
        (educations: any[]) => {
          // Loop through the fetched educations and populate the form array
          educations.forEach((education) => {
            this.educationForms.push({
              degree: education.degree,
              institutionId: education.institutionId,
              startDate: new Date(education.startDate)
                .toISOString()
                .split('T')[0], // Convert to yyyy-mm-dd format
              endDate: new Date(education.endDate).toISOString().split('T')[0], // Convert to yyyy-mm-dd format
              isPublic: education.public,
            });
          });
          console.log('Fetched educations:', this.educationForms);
        },
        (error) => {
          console.error('Error fetching educations', error);
        }
      );
  }

  // Method to add a new education form
  addEducationForm(): void {
    this.educationForms.push({
      degree: '',
      institutionId: '',
      startDate: '',
      endDate: '',
      isPublic: false,
    });
  }

  // Method to remove an education form by index
  removeEducationForm(index: number): void {
    this.educationForms.splice(index, 1);
  }

  // Submit all forms in one batch
  onSubmit(): void {
    const token = this.authService.getToken(); // Retrieve the stored token

    if (!token) {
      console.error('No token found, cannot make authenticated request');
      return;
    }

    // Validate that all fields in each form are filled
    const invalidForms = this.educationForms.some(
      (form) =>
        !form.degree || !form.institutionId || !form.startDate || !form.endDate
    );

    if (invalidForms) {
      console.error('Please fill out all fields.');
      return;
    }

    // Set the Authorization header with the token
    let headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    // Batch submit all education forms in one request
    this.http
      .post(
        'http://localhost:8080/api/v1/employee/auth/add/educations',
        this.educationForms,
        { headers }
      )
      .subscribe(
        (response) => {
          console.log('All educations saved successfully', response);
          alert('Educations saved successfully!'); // Show success feedback
        },
        (error) => {
          console.error('Error saving educations', error);
          alert('Error saving educations. Please try again.'); // Show error feedback
        }
      );
  }
}
