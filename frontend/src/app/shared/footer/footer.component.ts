import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent {
  public company = '';
  public title = '';
  public version = '';

  constructor() { }

  ngOnInit(): void {
    this.company = 'Global.company';
    this.title = 'Global.title';
    this.version = 'Global.version';
  }
}
