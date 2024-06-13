import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-value-status-change',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './value-status-change.component.html',
  styleUrl: './value-status-change.component.css'
})
export class ValueStatusChangeComponent {
  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(5)]],
      email: ['', [Validators.required, Validators.email]]
    }
    )
  }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.


    //fot getting the value changes in form for all inputs
    this.form.valueChanges.subscribe(formData => {
      console.log(formData)
    })

    //fot getting the value changes in form for individual inputs
    // this.form.get('name')?.valueChanges.subscribe(name => {
    //   console.log(name)
    // })

    //for status changes of the whole form if want individual then use .get as above 
    this.form.statusChanges.subscribe(status => {
      console.log(status)
    })
  }

  onSubmit() {

  }

}
