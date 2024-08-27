import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiBaseUrl = 'http://localhost:8080/api/v1/employee'; // Base URL for your API

  constructor(private http: HttpClient) {}

  // Method to get user profile settings
  getUserProfileSettings(): Observable<any> {
    const token = localStorage.getItem('token'); // Retrieve the token using the key 'token'
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });
    console.log(headers);
    return this.http.get<any>(`${this.apiBaseUrl}/users`, {
      headers: headers,
      withCredentials: true,
    });
  }

  // Method to get user reviews
  getUserReviews(): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });

    return this.http.get<any>(`${this.apiBaseUrl}/users/me`, {
      headers: headers,
      withCredentials: true,
    });
  }
}
