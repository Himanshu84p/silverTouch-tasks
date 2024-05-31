import { Routes } from '@angular/router';
import { FormComponent } from './form/form.component';
import { HomeComponent } from './home/home.component';

//routes to navigate component
export const routes: Routes = [
  {
    path: '',
    title: 'Home',
    component: HomeComponent,
  },
  {
    path: 'signup',
    title: 'Signup',
    component: FormComponent,
  },
  {
    path: 'login',
    title: 'Login',
    loadComponent: () =>
      import('./login-form/login-form.component').then(
        (m) => m.LoginFormComponent
      ),
  },
  {
    path: 'error',
    title: 'Error',
    loadComponent: () =>
      import('./error/error.component').then(
        (m) => m.ErrorComponent
      ),
  },
];
