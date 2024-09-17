import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatSelect, MatSelectChange } from '@angular/material/select';

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

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadSkills();
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
  // Submit selected skills to the backend
  submitSkills(): void {
    const payload = { selectedSkillIds: this.selectedSkills };

    this.http
      .post('http://localhost:8080/api/employee/auth/add/skills', payload) // Replace with your actual API endpoint
      .subscribe(
        (response) => {
          console.log('Skills submitted successfully', response);
          alert('Skills submitted successfully!');
        },
        (error) => {
          console.error('Error submitting skills', error);
          alert('Error submitting skills.');
        }
      );
  }
}
