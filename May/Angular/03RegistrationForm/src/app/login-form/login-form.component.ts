import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css',
})
export class LoginFormComponent {
  loginForm: FormGroup;
  isSubmitted = false;
  hide = true;
  errorMessage: string = '';
  //defining constructor for form using formbuilder
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  //function which returns the control : for reusability
  getUserFormControl(value: string) {
    return this.loginForm.controls[value];
  }

  //method for validating the form
  validateForm() {
    return {
      email: this.getUserFormControl('email'),
      password: this.getUserFormControl('password'),
    };
  }

  //on submit function checks the valid form input or not
  onSubmit() {
    try {
      this.isSubmitted = true;
      if (this.loginForm.valid) {
        this.authService.login(this.loginForm.value).subscribe(
          (response) => {
            console.log('Registration successful', response);
            this.router.navigate(['/']);
          },
          (error) => {
            console.log('Registration failed', error);
            this.errorMessage = 'Enter Valid Credentials';
          }
        );
        console.log('Form Submitted and data is here', this.loginForm.value);
      } else {
        console.log('Error in submitting form data');
      }
    } catch (error) {
      throw error;
    }
  }
}
