import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './modules/auth/components/login/login.component';

const routes: Routes = [
  { path: '', component: LoginComponent }, // Ruta vacía (inicio) muestra el LoginComponent
  // Otras rutas de tu aplicación aquí, por ejemplo: { path: 'dashboard', component: DashboardComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
