import { Injectable } from '@angular/core';
import {
  CanActivate,
  Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
} from '@angular/router';
import { AuthService } from './services/auth/auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    const token = this.authService.getToken(); // Get the token from the AuthService

    if (token) {
      // Case 1: User is logged in (has a token)
      if (state.url === '/login' || state.url === '/register') {
        // If the user tries to access the login or register page, redirect them to /home
        this.router.navigate(['/home']);
        return false; // Prevent navigation to login/register
      }
      return true; // Allow access to any other route
    } else {
      // Case 2: User is not logged in (no token)
      if (state.url === '/login' || state.url === '/register') {
        return true; // Allow access to login/register
      }
      // If the user tries to access any other route, redirect them to /login
      this.router.navigate(['/login']);
      return false; // Prevent navigation to protected routes
    }
  }
}
