import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {
  MatSelect,
  MatSelectChange,
  MatSelectModule,
} from '@angular/material/select';
import { AuthService } from '../services/auth/auth.service';
import { AlertService } from '../services/alert.service';

interface Skill {
  id: number;
  name: string;
}

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss'],
})
export class SkillsComponent implements OnInit {
  skills: Skill[] = []; // List of all available skills
  selectedSkills: number[] = []; // List of selected skill IDs

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private alertService: AlertService
  ) {}

  ngOnInit(): void {
    this.loadSkills();
    this.loadUserSkills();
  }

  // Load user's already selected skills
  loadUserSkills(): void {
    const token = this.authService.getToken(); // Retrieve the stored token
    if (!token) {
      console.error('No token found, cannot make authenticated request');
      return;
    }

    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    // Get user's skills and pre-select them
    this.http
      .get('http://localhost:8080/api/v1/employee/auth/me/skills', { headers })
      .subscribe(
        (response: any) => {
          this.selectedSkills = response.map((skill: Skill) => skill.id); // Explicitly type 'skill'
          console.log('Selected skill IDs:', this.selectedSkills);
        },
        (error) => {
          console.error('Error loading user skills', error);
        }
      );
  }
  isSelectOpen = false; // Track whether the select is open or closed

  // This method will toggle the state when the user opens or closes the dropdown
  onToggleChange() {
    this.isSelectOpen = !this.isSelectOpen; // Toggle the state when the dropdown is opened or closed
  }

  // This method toggles the dropdown when the button is clicked
  toggleSelect(skillSelect: MatSelect) {
    if (this.isSelectOpen) {
      skillSelect.close(); // Close the dropdown if it's open
    } else {
      skillSelect.open(); // Open the dropdown if it's closed
    }
  }

  // Fetch all available skills from the backend
  loadSkills(): void {
    this.http
      .get<Skill[]>('http://localhost:8080/api/skills/all') // Replace with your actual API endpoint
      .subscribe(
        (data) => {
          this.skills = data;
        },
        (error) => {
          console.error('Error fetching skills', error);
        }
      );
  }
  // Function to toggle skill selection
  toggleSkillSelection(skillId: number) {
    if (this.selectedSkills.includes(skillId)) {
      // Remove the skill if it is already selected
      this.selectedSkills = this.selectedSkills.filter((id) => id !== skillId);
    } else {
      // Add the skill if it is not already selected
      this.selectedSkills.push(skillId);
    }
  }

  // Function to handle skill selection change
  onSkillsChange(event: any) {
    const selectedOptions = Array.from(event.target.selectedOptions).map(
      (option: any) => parseInt(option.value, 10)
    );
    this.selectedSkills = selectedOptions;
  }
  submitSkills(): void {
    const token = this.authService.getToken(); // Retrieve the stored token
    const payload = this.selectedSkills; // Directly send the array of selected skill IDs

    if (!token) {
      console.error('No token found, cannot make authenticated request');
      return;
    }

    // Set the headers with the Bearer token
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    this.http
      .post('http://localhost:8080/api/v1/employee/auth/add/skills', payload, {
        headers,
      }) // Send the array directly
      .subscribe(
        (response) => {
          console.log('Skills submitted successfully', response);
          this.alertService.showAlert('success', 'Skills updated successfully');
        },
        (error) => {
          console.error('Error submitting skills', error);
          alert('Error submitting skills.');
        }
      );
  }
}
