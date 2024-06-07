import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { CustomPipePipe } from './custom-pipe.pipe';

@Component({
  selector: 'app-pipes',
  standalone: true,
  imports: [CommonModule, CustomPipePipe],
  templateUrl: './pipes.component.html',
  styleUrl: './pipes.component.css'
})
export class PipesComponent {
  price = 100.24; 
  todayDate: Date = new Date();
  name:string = "Himanshu";
}
