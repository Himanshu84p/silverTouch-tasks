import { Component, HostListener } from '@angular/core';
import { ButtonComponent } from '../button/button.component';
import { DisplayComponent } from '../display/display.component';
import { NgClass, NgFor } from '@angular/common';

@Component({
  selector: 'app-calculator',
  standalone: true,
  imports: [ButtonComponent, DisplayComponent, NgFor, NgClass],
  templateUrl: './calculator.component.html',
  styleUrl: './calculator.component.css',
})
export class CalculatorComponent {
  previousValue: string = '';
  displayValue: string = '';
  lastOperator: string = '';

  buttons = [
    { label: '%', type: 'operator' },
    { label: 'C', type: 'operator' },
    { label: '←', type: 'operator' },
    { label: '√', type: 'operator' },
    { label: '/', type: 'operator' },
    { label: '7' },
    { label: '8' },
    { label: '9' },
    { label: '^', type: 'operator' },
    { label: '*', type: 'operator' },
    { label: '4' },
    { label: '5' },
    { label: '6' },
    { label: '-', type: 'operator' },
    { label: '+', type: 'operator' },
    { label: '1' },
    { label: '2' },
    { label: '3' },
    { label: '0' },
    { label: '.' },
    { label: '=', type: 'equals' }, // Set type to 'equals' for the "=" button
  ];
  //function which handles the keyboard keys press
  @HostListener('window:keydown', ['$event'])
  handleKeyDown(event: KeyboardEvent) {
    const keyMap: any = {
      '0': '0',
      '1': '1',
      '2': '2',
      '3': '3',
      '4': '4',
      '5': '5',
      '6': '6',
      '7': '7',
      '8': '8',
      '9': '9',
      '+': '+',
      '-': '-',
      '*': '*',
      '/': '/',
      '%': '%',
      '^': '^',
      '.': '.',
      Enter: '=',
      Backspace: '←',
      Delete: 'C',
    };
    if (keyMap[event.key]) {
      this.onButtonClick(keyMap[event.key]);
    }
  }

  //redirecting the method according the operation performs
  onButtonClick(value: string) {
    if (value === 'C') {
      this.clear();
    } else if (value === '←') {
      this.backspace();
    } else if (value === '.') {
      this.addDot();
    } else if (value === '=') {
      this.calculate();
    } else if (value === '√') {
      this.squareRoot();
    } else if (['+', '-', '*', '/', '%', '^'].includes(value)) {
      this.setOperator(value);
    } else {
      this.appendValue(value);
    }
  }

  //backspace handle function
  backspace() {
    // Remove the last character from the displayValue
    this.displayValue = this.displayValue.slice(0, -1);
  }

  //Add Dot  handle function
  addDot() {
    const lastChar = this.displayValue.slice(-1);
    const operators = ['+', '-', '*', '/', '%', '^'];

    // If last character is an operator or empty, add '0.' instead
    if (operators.includes(lastChar) || this.displayValue === '') {
      this.displayValue += '0.';
    } else {
      // Split displayValue by operators to get the current number being typed
      const currentNumber = this.displayValue.split(/[\+\-\*\/\%\^]/).pop();

      // If the current number doesn't contain a dot, add one
      if (!currentNumber?.includes('.')) {
        this.displayValue += '.';
      }
    }
  }

  //Append Values handle function
  appendValue(value: string) {
    // Clear display if it shows 'Error', 'Infinity', or 'NaN'
    if (
      this.displayValue === 'Error' ||
      this.displayValue === 'Infinity' ||
      this.displayValue === 'NaN'
    ) {
      this.displayValue = '';
    }
    if (this.lastOperator && !isNaN(parseFloat(value))) {
      this.displayValue += value;
    } else if (!isNaN(parseFloat(value))) {
      if (this.displayValue === '0' && value === '0') {
        this.displayValue = '0';
        return;
      }
      if (this.displayValue === '0' && value != '0') {
        this.displayValue = value;
      } else {
        this.displayValue += value;
      }
    }
  }

  //setting the operator
  setOperator(operator: string) {
    if (
      this.displayValue !== '' &&
      !isNaN(parseFloat(this.displayValue.slice(-1)))
    ) {
      this.displayValue += operator;
      this.lastOperator = operator;
    } else if (this.displayValue !== '') {
      this.displayValue = this.displayValue.slice(0, -1) + operator;
    }
  }

  //square root function
  squareRoot() {
    if (this.displayValue !== '') {
      this.previousValue = `√(${this.displayValue})`;
      this.displayValue = String(Math.sqrt(parseFloat(this.displayValue)));
    }
  }

  //calculating the total value in the display
  calculate() {
    let lastChar: String = this.displayValue.slice(-1);
    try {
      if (this.displayValue != '' && lastChar != '.') {
        this.previousValue = this.displayValue;
        this.displayValue = String(eval(this.displayValue.replace('^', '**')));
        this.lastOperator = '';
      }
    } catch (e) {
      this.displayValue = 'Error';
    }
  }

  //clearing the input fields
  clear() {
    this.previousValue = '';
    this.displayValue = '';
    this.lastOperator = '';
  }
}
