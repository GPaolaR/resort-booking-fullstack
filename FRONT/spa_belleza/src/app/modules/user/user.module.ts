import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SelectPlanComponent } from './components/select-plan/select-plan.component';
import { MakeAppointmentComponent } from './components/make-appointment/make-appointment.component';
import { AppointmentHistoryComponent } from './components/appointment-history/appointment-history.component';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  declarations: [
    SelectPlanComponent,
    MakeAppointmentComponent,
    AppointmentHistoryComponent,
    
  ],
  imports: [
    CommonModule,
    SharedModule
  ]
})
export class UserModule { }
