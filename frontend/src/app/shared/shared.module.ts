import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenubarModule } from 'primeng/menubar';
import { BrowserAnimationsModule }  from '@angular/platform-browser/animations';

import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { DateFormatPipe } from './pipes/date-format/date-format.pipe';
import { CurrencyFormatPipe } from './pipes/currency-format/currency-format.pipe';


@NgModule({
  declarations: [    
    FooterComponent,
    HeaderComponent,
    DateFormatPipe,
    CurrencyFormatPipe
    ],
  imports: [
    CommonModule,    
    MenubarModule,
    
    BrowserAnimationsModule
    //HeaderComponent   
  ],
  exports:[
    FooterComponent,
    HeaderComponent,
    DateFormatPipe,
    CurrencyFormatPipe
  ]
})
export class SharedModule { }