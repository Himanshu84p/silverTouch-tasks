import { Router } from '@angular/router';
import { AuthService } from './../auth.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  constructor(public authService: AuthService, private router: Router) {
    //navigate user as per login
    if (authService.isLoggedIn()) {
      this.router.navigate(['/']);
      console.log(localStorage.getItem('user'));
    } else {
      this.router.navigate(['login']);
    }
  }
}
