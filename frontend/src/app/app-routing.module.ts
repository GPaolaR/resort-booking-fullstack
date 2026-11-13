import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';


const routes: Routes = [
  { path:'auth', loadChildren: () => import('./modules/auth/auth-routing.module').then(m => m.AuthRoutingModule)},
  { path:'admin', loadChildren: () => import('./modules/admin/admin-routing.module').then(m => m.AdminRoutingModule)},
  { path:'user', loadChildren: () => import('./modules/user/user-routing.module').then(m => m.UserRoutingModule)},
  { path: '', redirectTo: 'auth/login', pathMatch: 'full' },
  { path: '**', redirectTo:'auth/login'}
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
