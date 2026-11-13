import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { CommonModule } from '@angular/common';

const routes: Routes = [
  {
    path: '',
    children:[
        { path:'login', component: LoginComponent},
        { path: '*', redirectTo:'login'}
    ] 
  },
  // Otras rutas específicas del módulo
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes)]
})
export class AuthRoutingModule { }
