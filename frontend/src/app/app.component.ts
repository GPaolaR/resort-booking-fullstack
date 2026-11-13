import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'spa_belleza';
  show: boolean = false;

  constructor(private router: Router) {
    console.log(this.show);
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.show = !(
          event.url === '/auth/login' ||
          event.url === '/auth' ||
          event.url === '/'
        );
      }
    });
  }
}
