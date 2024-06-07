import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-two-way-databind',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './two-way-databind.component.html',
  styleUrl: './two-way-databind.component.css'
})
export class TwoWayDatabindComponent {
  message: string = 'hey there!!!'

}
