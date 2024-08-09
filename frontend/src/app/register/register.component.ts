import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'] // corrected styleUrls to plural
})

export class RegisterComponent {

  employeename: string = "";
  lastname: string = "";
  email: string = "";
  phone: string = "";
  password: string = "";
  confirmPassword: string = "";

  constructor(private http: HttpClient) { }

  save(registerForm: NgForm) {
    // Check if the form is valid
    if (!registerForm.valid) {
      // Handle the invalid form case
     
      return;
    }

    // Check if passwords match
    if (this.confirmPassword !== this.password) {
      alert("Password and confirm password fields must match!");
      return;
    }

    // If form is valid and passwords match, proceed with the HTTP request
    let bodyData = {
      "employeename": this.employeename,
      "email": this.email,
      "password": this.password,
      "employeelastname": this.lastname,
      "phone": this.phone
    };

    this.http.post("http://localhost:8080/api/v1/employee/save", bodyData, { responseType: "text" })
      .subscribe((resultData: any) => {
        console.log(resultData);
        alert("Employee Registered Successfully");
      });
  }
}
