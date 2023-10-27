import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
  { path:'auth', loadChildren: () => import('./modules/auth/auth-routing.module').then(m => m.AuthRoutingModule)},
  { path:'admin', loadChildren: () => import('./modules/admin/admin-routing.module').then(m => m.AdminRoutingModule)},
  { path:'user', loadChildren: () => import('./modules/user/user-routing.module').then(m => m.UserRoutingModule)},
  //{ path: '', redirectTo:'auth'},
  { path: '*', redirectTo:'auth'}
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
