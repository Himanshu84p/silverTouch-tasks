import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
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
import { Router, RouterLink } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { HotToastService } from '@ngxpert/hot-toast';

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
    RouterLink
  ],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css',
})
export class LoginFormComponent {
  loginForm: FormGroup;
  isSubmitted = false;
  hide = true;
  errorMessage: string = '';
  private toastService = inject(HotToastService);
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

  showSuccessToast(message: string) {
    this.toastService.success(message)
  }
  showErrorToast(message: string) {
    this.toastService.error(message)
  }


  //on submit function checks the valid form input or not
  onSubmit() {
    try {

      this.isSubmitted = true;

      if (this.loginForm.valid) {
        this.toastService.loading("Signing in....", { "id": "sign_in" })
        this.authService.login(this.loginForm.value).subscribe(
          (response) => {
            console.log('Registration successful', response);
            this.toastService.close("sign_in")
            this.showSuccessToast("Login SuccessFully")
            this.router.navigateByUrl('dashboard/home');
          },
          (error: HttpErrorResponse) => {
            console.log('Registration failed', error.error.msg);
            this.toastService.close("sign_in")
            this.showErrorToast(error.error.msg)
            this.errorMessage = error.error.msg;
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
