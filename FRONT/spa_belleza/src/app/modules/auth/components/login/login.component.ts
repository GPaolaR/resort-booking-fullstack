import { Component } from '@angular/core';
import { ApiLoginService } from '../../services/api-login.service';
import { FormBuilder, Validators} from "@angular/forms";
import { Message, MessageService} from "primeng/api";
import { Router } from '@angular/router';
import { switchMap, catchError } from 'rxjs/operators';
import { of, EMPTY } from 'rxjs';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {  
  credentialUser =this.fb.group({
    username:['',Validators.required],
    password: ['',Validators.required]
  });
  membershipType: any;
  membershipTypeId: any;
  userName: any;
  loginId: any;
  roleName: any;

  constructor(private apiLoginService: ApiLoginService,
    private fb: FormBuilder,
    private messageService: MessageService,
    private router: Router){}
  
  ngOnInit(): void {
    localStorage.clear();
  }

  btLogin(){
    this.apiLoginService.validateUser(this.credentialUser.value).pipe(
      switchMap((response: any) => {
        this.loginId=response.loginId;
        this.roleName=response.role.rolName;
        localStorage.setItem('loginId', response.loginId);
        localStorage.setItem('rol', this.roleName);
    
        if (response.role && this.roleName === 'Usuario') {
          return this.apiLoginService.getUser(this.loginId);
        } else if (response.role && this.roleName === 'Administrador') {
          return this.apiLoginService.getAdmin(this.loginId);
        } else {
          return of(null); // No se realiza una segunda llamada para otros roles
        }
      }),
      catchError(error => {
        const errorMessage = error.error.message || 'Ocurrió un error inesperado';
        this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
        return EMPTY; // Devuelve un observable vacío en caso de error
      })
    ).subscribe((data: any) => {
      if (data) {        
        if (this.roleName === 'Usuario') {
          this.membershipTypeId=data.membershipType.membershipTypeId;
          this.membershipType=data.membershipType.membershipType;
          this.userName=data.userName + ' ' + data.userLastName;
          localStorage.setItem('name', this.userName);
          localStorage.setItem('membershipTypeId', this.membershipTypeId);
          localStorage.setItem('membershipType', this.membershipType);
          this.router.navigate(['user/home']); // Redirige al home de usuario
        } else if (this.roleName === 'Administrador') {
          this.userName=data.administratorName + ' ' + data.administratorLastName;
          localStorage.setItem('name', this.userName);
          this.router.navigate(['admin/home']); // Redirige al home de admin
        }
      }
    });
    
  }
 
}
