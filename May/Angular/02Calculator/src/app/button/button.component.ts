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
  // label for btn text
  @Input() label: string = '';
  //btn type operator || operand || equal
  @Input() buttonType: string | undefined = '';
  //btn click event to handle click
  @Output() buttonClick = new EventEmitter<string>();

  onClick() {
    this.buttonClick.emit(this.label);
  }
}
