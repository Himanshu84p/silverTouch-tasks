import { Component, EventEmitter, ViewEncapsulation } from '@angular/core';
//this class's constructor will not call default we have to give providers in metadata
//TODO : all other class should be above @Component angular not allowed multiple class in standalone component 
class demo {
  constructor() {
    console.log("hello demo")
  }
}

@Component({
  selector: 'app-children',
  standalone: true,
  imports: [],
  templateUrl: './children.component.html',
  styleUrl: './children.component.css',
  //prevent white spaces if false ; add white spaces between elements if true
  preserveWhitespaces: true,
  viewProviders: [demo],
  //encapsulation more in readme file
  // encapsulation:ViewEncapsulation.Emulated

  //for parent child communication
  inputs: ['Name'],
  outputs: ['childInput']


})


export class ChildrenComponent {
  Name: string = "";
  city:string = 'Ahmedabad';
  constructor(private _demo: demo) {
    console.log("main children is calling")
  }

  childInput = new EventEmitter();
  getInput(val: any) {
    this.childInput.emit(val)
  }

  changeCity() {
    this.city = "Mehsana"
  }

}
