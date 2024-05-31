import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatRadioModule } from '@angular/material/radio';

import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    MatCheckboxModule,
    MatRadioModule,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css',
})
export class FormComponent {
  form: FormGroup;
  isSubmitted = false;
  hide = true;
  //defining constructor for form using formbuilder
  constructor(private fb: FormBuilder, private authService: AuthService) {
    this.form = this.fb.group({
      firstname: ['', [Validators.required, Validators.minLength(3)]],
      lastname: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]],
      gender: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  //function which returns the control : for reusability
  getUserFormControl(value: string) {
    return this.form.controls[value];
  }

  //method for validating the form
  validateForm() {
    return {
      firstname: this.getUserFormControl('firstname'),
      lastname: this.getUserFormControl('lastname'),
      email: this.getUserFormControl('email'),
      phone: this.getUserFormControl('phone'),
      gender: this.getUserFormControl('gender'),
      password: this.getUserFormControl('password'),
    };
  }

  //on submit function checks the valid form input or not
  onSubmit() {
    try {
      this.isSubmitted = true;
      if (this.form.valid) {
        this.authService.register(this.form.value).subscribe(
          (response) => {
            console.log('Registration successful', response);
          },
          (error) => {
            console.log('Registration failed', error);
          }
        );
        console.log('Form Submitted and data is here', this.form.value);
        alert(
          `Form Submitted Successfully , name : ${this.form.value['firstname']},  lastname : ${this.form.value['lastname']}, email : ${this.form.value['email']}, phone : ${this.form.value['phone']}`
        );
      } else {
        console.log('Error in submitting form data');
      }
    } catch (error) {
      throw error;
    }
  }
}
