import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AgendaComponent } from './components/agenda/agenda.component';
import { SpecialistAgendaComponent } from './components/specialist-agenda/specialist-agenda.component';
import { ManagementComponent } from './components/management/management.component';



@NgModule({
  declarations: [
    AgendaComponent,
    SpecialistAgendaComponent,
    ManagementComponent
  ],
  imports: [
    CommonModule
  ]
})
export class AdminModule { }
