import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ChildrenComponent } from './children/children.component';
import { ParentComponent } from './parent/parent.component';
import { InterpolationComponent } from './interpolation/interpolation.component';
import { AttributeBindingComponent } from './attribute-binding/attribute-binding.component';
import { TwoWayDatabindComponent } from './two-way-databind/two-way-databind.component';
import { PipesComponent } from './pipes/pipes.component';
import { Comp1Component } from './serviceDemo/comp1/comp1.component';
import { Comp2Component } from './serviceDemo/comp2/comp2.component';
import { NavbarComponent } from './routingDemo/navbar/navbar.component';
import { CookieDemoComponent } from './cookie-demo/cookie-demo.component';
import { ValueStatusChangeComponent } from './value-status-change/value-status-change.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ChildrenComponent, ParentComponent, InterpolationComponent, AttributeBindingComponent, TwoWayDatabindComponent, PipesComponent, Comp1Component, Comp2Component, NavbarComponent, CookieDemoComponent, ValueStatusChangeComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = '04DirectoryMeta';
}
