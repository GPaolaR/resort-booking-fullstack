import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AppointmentHistoryComponent } from './components/appointment-history/appointment-history.component';
import { MakeAppointmentComponent } from './components/make-appointment/make-appointment.component';
import { SelectPlanComponent } from './components/select-plan/select-plan.component';

const routes: Routes = [
  {
    path: '',
    children:[
        { path:'history', component: AppointmentHistoryComponent},
        { path:'appointment', component: MakeAppointmentComponent},
        { path:'home', component: SelectPlanComponent},
        //{ path: '', redirectTo:'home'},
        { path: '*', redirectTo:'home'}
    ] 
  },
  // Otras rutas específicas del módulo
];
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],

})
export class UserRoutingModule { }
