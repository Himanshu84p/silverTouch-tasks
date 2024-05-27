import { Component, EventEmitter, Input, Output } from '@angular/core';
import { NgClass, NgIf } from '@angular/common';

@Component({
  selector: 'app-button',
  standalone: true,
  imports: [NgIf, NgClass],
  templateUrl: './button.component.html',
  styleUrl: './button.component.css',
})
export class ButtonComponent {
  @Input() label: string = '';
  @Input() buttonType: string | undefined = ''; // Add input for button type
  @Output() buttonClick = new EventEmitter<string>();

  onClick() {
    this.buttonClick.emit(this.label);
  }
}
