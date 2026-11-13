import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { PrimeNGModule } from '../prime-ng/prime-ng.module';

import { FormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';


@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    PrimeNGModule,
    HttpClientModule,
    FormsModule
  ]
})
export class AuthModule { }
