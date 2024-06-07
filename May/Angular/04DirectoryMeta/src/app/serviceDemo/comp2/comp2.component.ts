import { Component } from '@angular/core';
import { DemoService } from '../demo.service';

@Component({
  selector: 'app-comp2',
  standalone: true,
  imports: [],
  templateUrl: './comp2.component.html',
  styleUrl: './comp2.component.css',
  providers: [DemoService]

})
export class Comp2Component {
  list: number[] = []
  constructor(private _demo: DemoService) {
    this.list = this._demo.getData()
  }

  addNumber(num: any) {
    let val = parseInt(num)
    this._demo.addNum(val)

  }

  getData() {
    this.list = this._demo.getData()
  }

}
