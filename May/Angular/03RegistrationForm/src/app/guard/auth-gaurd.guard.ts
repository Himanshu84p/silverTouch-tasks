
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { afterNextRender, inject } from '@angular/core';

export const authGaurdGuard: CanActivateFn = (route, state) => {
  // let authService = inject(AuthService);
  let router = inject(Router)

  const loggedIn = localStorage.getItem('accessToken')

  if (loggedIn != null) {
    return true
  } else {
    router.navigateByUrl('auth/login')
    return false
  }
};
