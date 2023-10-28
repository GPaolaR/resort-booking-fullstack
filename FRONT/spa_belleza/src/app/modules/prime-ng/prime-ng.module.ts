import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// Reactive form for generate and validate the form
import { ReactiveFormsModule } from '@angular/forms'; 
import { DividerModule } from 'primeng/divider';
import { ButtonModule } from 'primeng/button';
import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MessagesModule } from 'primeng/messages';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { MenubarModule } from 'primeng/menubar';
import { CarouselModule } from 'primeng/carousel';
import { TagModule } from 'primeng/tag';
import { CardModule } from 'primeng/card';
import { ScrollPanelModule } from 'primeng/scrollpanel';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    MessagesModule
  ],
  providers: [MessageService],
  exports:[
    ReactiveFormsModule,
    DividerModule,
    ButtonModule,
    PasswordModule,
    InputTextModule,
    BrowserAnimationsModule,
    MessagesModule,
    ToastModule,
    MenubarModule,
    CarouselModule,
    TagModule,
    CardModule,
    ScrollPanelModule
  ]
})
export class PrimeNGModule { }
