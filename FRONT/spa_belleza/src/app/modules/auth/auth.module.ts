import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';

import { CardModule } from 'primeng/card';
import { DividerModule } from 'primeng/divider';
import { ButtonModule } from 'primeng/button';
import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';


@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    CardModule,
    DividerModule,
    ButtonModule,
    PasswordModule,
    InputTextModule
  ]
})
export class AuthModule { }
