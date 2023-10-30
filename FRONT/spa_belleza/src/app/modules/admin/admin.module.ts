import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AgendaComponent } from './components/agenda/agenda.component';
import { SpecialistAgendaComponent } from './components/specialist-agenda/specialist-agenda.component';
import { ManagementComponent } from './components/management/management.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { PrimeNGModule } from '../prime-ng/prime-ng.module';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AgendaComponent,
    SpecialistAgendaComponent,
    ManagementComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    PrimeNGModule,
    FormsModule
  ]
})
export class AdminModule { }
