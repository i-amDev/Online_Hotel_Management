import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const authservice = inject(AuthService);
  const routeservice = inject(Router);
  if(authservice.isLoggedIn()) {
    return true;
  }
  return routeservice.navigate(['/login']);
};
