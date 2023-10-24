import { Component } from '@angular/core';
import { ApiLoginService } from '../../services/api-login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  credentialUser: any={
    USERNAME:'',
    PASSWORD_ENCRYPTED: ''
  }

  constructor(private apiLoginService: ApiLoginService){}

  btLogin(){
    console.log('user:', this.credentialUser.USERNAME);
    console.log('contre:', this.credentialUser.PASSWORD_ENCRYPTED);

    // Llama al servicio para enviar la información del cliente
    this.apiLoginService.validateUser(this.credentialUser).subscribe((response: any) => {
      // Maneja la respuesta del servidor aquí
      console.log('Mensaje:', response);
      // Puedes realizar otras acciones después de guardar
    });
  }
}
