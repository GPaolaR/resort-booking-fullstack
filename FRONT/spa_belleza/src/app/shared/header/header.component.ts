import { Component, Input } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  
 userMenuItems: MenuItem[] | undefined;
 adminMenuItems: MenuItem[] | undefined;
 menuItems: MenuItem[] | undefined;
 rol:String | null ='';
 name:String | null ='Mi cuenta';
 membershipType:String | null ='Informacion';

  ngOnInit() {  
    this.rol = localStorage.getItem('rol');
    this.name = localStorage.getItem('name');
    this.membershipType = localStorage.getItem('membershipType');

    this.userMenuItems = [
      { label: 'Home', routerLink: '/user/home' },
      { label: 'Citas', items:[
          { label: 'Solicita tu cita', routerLink: '/user/appointment' },
          { label: 'Tus citas', routerLink: '/user/history'  }
      ]
      },
      { label: '' + this.name, items:[
          { label: 'Membresia: '+this.membershipType?.toUpperCase() },
          { label: 'Cerrar Sesión', routerLink: '/auth/login' }
        ]
      }
    ];

    this.adminMenuItems = [
      { label: 'Agenda', items: [
          { label: 'Agenda General', routerLink: '/admin/home' },
          { label: 'Agenda Especialista', routerLink: '/admin/specialist' }
        ]
      },
      { label: 'Gestión Citas', routerLink: '/admin/management' },
      { label: '' + this.name, items:[
          { label: 'Cerrar Sesión',routerLink: '/auth/login'  }
        ]
      }
    ];
    
    if(this.rol=='Usuario'){
      this.menuItems=this.userMenuItems;      
    }
    else if(this.rol=='Administrador'){
      this.menuItems=this.adminMenuItems;      
    }
  }
}
