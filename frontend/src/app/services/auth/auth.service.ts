import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private token: string | null = null;
  private expiresIn: number | null = null;

  setToken(token: string, expiresIn: number) {
    this.token = token;
    this.expiresIn = expiresIn;
    // Store the token and expiration time in local storage
    localStorage.setItem('token', token);
    localStorage.setItem('expiresIn', expiresIn.toString());
  }

  getToken(): string | null {
    // Check if the token is already in memory
    if (this.token) {
      return this.token;
    }

    // Otherwise, retrieve it from local storage
    const storedToken = localStorage.getItem('token');
    if (storedToken) {
      this.token = storedToken;
      this.expiresIn = Number(localStorage.getItem('expiresIn'));
    }

    return this.token;
  }
  getExpiresIn(): number | null {
    // Check if the expiration time is already in memory
    if (this.expiresIn !== null) {
      return this.expiresIn;
    }

    // Otherwise, retrieve it from local storage
    const storedExpiresIn = localStorage.getItem('expiresIn');
    if (storedExpiresIn) {
      this.expiresIn = Number(storedExpiresIn);
    }

    return this.expiresIn;
  }

  clearToken() {
    this.token = null;
    this.expiresIn = null;

    // Clear the token and expiration time from local storage
    localStorage.removeItem('token');
    localStorage.removeItem('expiresIn');
  }
}
