import { Component, inject } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { FormComponent } from './form/form.component';
import { HomeComponent } from './home/home.component';
import { RouterLink } from '@angular/router';
import { LoginFormComponent } from './login-form/login-form.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from './auth.service';
import { ErrorComponent } from './error/error.component';
import { CommonModule } from '@angular/common';
import { HotToastService } from '@ngxpert/hot-toast';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    FormComponent,
    HomeComponent,
    RouterLink,
    LoginFormComponent,
    HttpClientModule,
    ErrorComponent,
    CommonModule,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers: [AuthService]
})
export class AppComponent {
  title = '03RegistrationForm';
  currUser : any = {};
    //service for toast  
    private toastService = inject(HotToastService);
  
  constructor(public authService: AuthService, private router: Router) {
    if (authService.isLoggedIn()) {
      this.currUser = JSON.parse(localStorage.getItem('user') || '{}');
      console.log('curr user', this.currUser);
    }
  }
  

  //for logout the user
  logout() {
    this.authService.logout();
    this.toastService.success("Logged Out Successfully!!")
    this.router.navigate(['login']);
  }
}
