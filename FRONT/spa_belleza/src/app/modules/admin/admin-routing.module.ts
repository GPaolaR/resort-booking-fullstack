import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AgendaComponent } from './components/agenda/agenda.component';
import { ManagementComponent } from './components/management/management.component';
import { SpecialistAgendaComponent } from './components/specialist-agenda/specialist-agenda.component';

const routes: Routes = [
  {
    path: '',
    children:[
        { path:'home', component: AgendaComponent},
        { path:'specialist', component: SpecialistAgendaComponent},
        { path:'management', component: ManagementComponent},
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
export class AdminRoutingModule { }
