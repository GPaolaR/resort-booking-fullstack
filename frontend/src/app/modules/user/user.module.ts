import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SelectPlanComponent } from './components/select-plan/select-plan.component';
import { MakeAppointmentComponent } from './components/make-appointment/make-appointment.component';
import { AppointmentHistoryComponent } from './components/appointment-history/appointment-history.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { PrimeNGModule } from '../prime-ng/prime-ng.module';
import { HttpClientModule} from '@angular/common/http';
import { PlanImagePipe } from './pipes/plan-image.pipe';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    SelectPlanComponent,
    MakeAppointmentComponent,
    AppointmentHistoryComponent,
    PlanImagePipe,
    
  ],
  imports: [
    CommonModule,
    SharedModule,
    PrimeNGModule,
    HttpClientModule,
    FormsModule
  ]
})
export class UserModule { }
