import { Routes } from '@angular/router';
import { FormComponent } from './form/form.component';
import { HomeComponent } from './home/home.component';
import { MainLayoutComponent } from './layouts/main-layout/main-layout.component';
import { authGaurdGuard } from './guard/auth-gaurd.guard';
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { ErrorComponent } from './error/error.component';

//routes to navigate component
export const routes: Routes = [
  {
    path: '',
    redirectTo: '/dashboard/home',
    pathMatch: 'full',
  },
  {
    path: 'dashboard',
    component: MainLayoutComponent,
    canActivate: [authGaurdGuard],
    children: [
      // {
      //   path: '',
      //   redirectTo: 'home',
      //   pathMatch: 'full'
      // },
      {
        path: 'home', component: HomeComponent, title: 'Home',
      },
      {
        path: '**', component: ErrorComponent, title: 'Unauthorized',
      },
    ]
  },
  {
    path: 'auth',
    component: AuthLayoutComponent,
    children: [
      { path: 'login', component: LoginFormComponent, title: "SignIn" },
      { path: 'register', component: FormComponent, title: "SignUp" },
      {
        path: '**', component: ErrorComponent, title: 'Unauthorized',
      },
    ]
  }
];
