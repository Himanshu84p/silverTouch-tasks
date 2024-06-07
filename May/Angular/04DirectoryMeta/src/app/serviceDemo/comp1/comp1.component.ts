import { Component } from '@angular/core';
import { DemoService } from '../demo.service';

@Component({
  selector: 'app-comp1',
  standalone: true,
  imports: [],
  templateUrl: './comp1.component.html',
  styleUrl: './comp1.component.css',
  //provided demoservice on the providers so it only limits to this component only
  providers: [DemoService]
})
export class Comp1Component {
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
