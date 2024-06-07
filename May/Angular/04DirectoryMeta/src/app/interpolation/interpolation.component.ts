import { Component } from '@angular/core';

@Component({
  selector: 'app-interpolation',
  standalone: true,
  imports: [],
  templateUrl: './interpolation.component.html',
  styleUrl: './interpolation.component.css'
})
export class InterpolationComponent {
  city: string = 'Ahmedabad';

  changeCity() {
    this.city = this.city === "Ahmedabad" ? "Mehsana" : "Ahmedabad"
    console.log("city changesd")
  }
}
