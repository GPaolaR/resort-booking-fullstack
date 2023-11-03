import { Component } from '@angular/core';
import { ApiLoginService } from '../../services/api-login.service';
import { FormBuilder, Validators} from "@angular/forms";
import { Message, MessageService} from "primeng/api";
import { Router } from '@angular/router';

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

  constructor(private apiLoginService: ApiLoginService,
    private fb: FormBuilder,
    private messageService: MessageService,
    private router: Router){}
  
  ngOnInit(): void {
    localStorage.clear();
  }

  btLogin(){
    // Llama al servicio para enviar la información del cliente
    this.apiLoginService.validateUser(this.credentialUser.value).subscribe(
      (response: any) => {
        // Maneja la respuesta del servidor aquí
        //console.log('Mensaje:', response);
        localStorage.setItem('loginId',response.loginId)
        localStorage.setItem('rol',response.role.rolName)
        if (response.role && response.role.rolName === 'Usuario') {
          this.router.navigate(['user/home']); // Redirige al home de usuario
        } else if (response.role && response.role.rolName === 'Administrador') {
          this.router.navigate(['admin/home']); // Redirige al home de admin
        }
      },
      (error: any) => {
        // Maneja los errores del servidor aquí
        const errorMessage = error.error.message || 'Ocurrió un error inesperado';
        this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
      }
    );
  }
 
}
