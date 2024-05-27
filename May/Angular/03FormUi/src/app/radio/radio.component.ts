import { Component, Input } from '@angular/core';
import {MatRadioModule} from '@angular/material/radio';
@Component({
  selector: 'app-radio',
  standalone: true,
  imports: [MatRadioModule],
  templateUrl: './radio.component.html',
  styleUrl: './radio.component.css'
})
export class RadioComponent {
  @Input() options :String[] = [];

}
