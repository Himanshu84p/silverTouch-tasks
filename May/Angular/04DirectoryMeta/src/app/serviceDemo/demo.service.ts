import { Injectable } from '@angular/core';

@Injectable(
  //if providedIn not set to the root then every component which uses this service must set it in the providers
  //   {
  //   providedIn: 'root'
  // }
)
export class DemoService {

  data: number[] = [100];
  constructor() { }

  addNum(val: number) {
    this.data.push(val)
  }
  getData() {
    return this.data
  }
}
