import { Routes } from '@angular/router';
import { ChildrenComponent } from './children/children.component';
import { ParentComponent } from './parent/parent.component';
import { InterpolationComponent } from './interpolation/interpolation.component';
import { CookieDemoComponent } from './cookie-demo/cookie-demo.component';
import { ValueStatusChangeComponent } from './value-status-change/value-status-change.component';

export const routes: Routes = [

    {
        path: "home",
        component: ChildrenComponent
    },
    {
        path: "aboutus",
        component: ParentComponent
    },
    {
        path: "cookie",
        component: CookieDemoComponent
    },
    {
        path: "valuechanges",
        component: ValueStatusChangeComponent
    },
    // {
    //     path: "sidebar",
    //     outlet: 'outlet2',
    //     component: InterpolationComponent
    // },

];
