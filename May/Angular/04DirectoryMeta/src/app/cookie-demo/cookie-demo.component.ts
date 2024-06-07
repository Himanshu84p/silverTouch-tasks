import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';


@Component({
  selector: 'app-cookie-demo',
  standalone: true,
  imports: [],
  templateUrl: './cookie-demo.component.html',
  styleUrl: './cookie-demo.component.css'
})
export class CookieDemoComponent {
  constructor(private _cookieService: CookieService) {
    console.log("Cookie set")
    _cookieService.set('token', 'himanshu')
    console.log(_cookieService.getAll())

  }

}
