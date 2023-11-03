import { Component, Input } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  //gfg: MenuItem[]; 
  /*constructor(private gfg: MenuItem[]) {};
  
  ngOnInit() { 
    this.gfg = [ 
      { 
        label: 'HTML', 
        items: [ 
          { 
            label: 'HTML 1'
          }, 
          { 
            label: 'HTML 2'
          } 
        ] 
      }, 
      { 
        label: 'Angular', 
  
        items: [ 
          { 
            label: 'Angular 1'
          }, 
          { 
            label: 'Angular 2'
          } 
        ] 
      } 
    ]; 
  } */
 
 // @Input() isAdmin: boolean = false; // Flag para verificar si el usuario es administrador
 userMenuItems: MenuItem[] | undefined;
 adminMenuItems: MenuItem[] | undefined;
 menuItems: MenuItem[] | undefined;
 rol:String | null ='';
 name:String |null ='';
 membershipType:String |null ='Informacion';

  ngOnInit() {  
    this.userMenuItems = [
      { label: 'Home', routerLink: '/user/home' },
      { label: 'Citas', items:[
          { label: 'Solicita tu cita', routerLink: '/user/appointment' },
          { label: 'Tus citas', routerLink: '/user/history'  }
      ]
      },
      { label: 'Mi cuenta' + this.name, items:[
          // { label: ''+this.membershipType },
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
      { label: 'Mi cuenta' + this.name, items:[
          { label: 'Cerrar Sesión',routerLink: '/auth/login'  }
        ]
      }
    ];
    this.rol = localStorage.getItem('rol');
    this.name = localStorage.getItem('name');
    if(this.rol=='Usuario'){
      this.menuItems=this.userMenuItems;
      this.membershipType = localStorage.getItem('membershipType');
    }
    else if(this.rol=='Administrador'){
      this.menuItems=this.adminMenuItems;      
    }
  }
}
