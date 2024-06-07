import { Component, ViewEncapsulation } from '@angular/core';
import { ChildrenComponent } from '../children/children.component';

@Component({
  selector: 'app-parent',
  standalone: true,
  imports: [ChildrenComponent],
  templateUrl: './parent.component.html',
  styleUrl: './parent.component.css',
  // encapsulation:ViewEncapsulation.ShadowDom
})
export class ParentComponent {

  val: any;
  getData(data : any) {
    this.val = data;
  }
}
