import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenubarModule } from 'primeng/menubar';
import { BrowserAnimationsModule }  from '@angular/platform-browser/animations';

import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';


@NgModule({
  declarations: [    
    FooterComponent,
    HeaderComponent
    ],
  imports: [
    CommonModule,    
    MenubarModule,
    
    BrowserAnimationsModule
    //HeaderComponent   
  ],
  exports:[
    FooterComponent,
    HeaderComponent
  ]
})
export class SharedModule { }