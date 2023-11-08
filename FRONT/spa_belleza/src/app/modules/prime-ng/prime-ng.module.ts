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
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { DataViewModule } from 'primeng/dataview';
import { MultiSelectModule } from 'primeng/multiselect';
import { InputNumberModule } from 'primeng/inputnumber';
import { TabViewModule } from 'primeng/tabview';

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
    ScrollPanelModule,
    CalendarModule,
    DropdownModule,
    TableModule,
    DataViewModule,
    MultiSelectModule,
    InputNumberModule,
    TabViewModule
  ]
})
export class PrimeNGModule { }
