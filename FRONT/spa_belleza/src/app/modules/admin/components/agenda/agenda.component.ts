import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ApiSpecialistsService } from '../../services/api-specialists/api-specialists.service';
import { specialist} from'../../interfaces/specialist.interface';
import { plan } from'../../interfaces/plan.interface';
import { user } from'../../interfaces/user.interface';
import { ApiUsersService } from '../../services/api-users/api-users.service';
import { ApiPlansService } from '../../services/api-plans/api-plans.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-agenda',
  templateUrl: './agenda.component.html',
  styleUrls: ['./agenda.component.scss']
})
export class AgendaComponent {
  inicialDate = new Date();
  endDate= new Date();
  
  specialists: specialist[] | undefined;
  users: user[] | undefined;
  plans: plan[] | undefined;

  selectedSpecialist: specialist | undefined;
  selectedUser: user | undefined;
  selectedPlan: plan | undefined;

  constructor(private cdr: ChangeDetectorRef,
    private apiSpecialistsService: ApiSpecialistsService,
    private apiUsersService: ApiUsersService,
    private apiPlansService: ApiPlansService
    ){}


  ngOnInit() {
     // Combina las observables de los servicios en una sola
    forkJoin([
      this.apiSpecialistsService.getSpecialists(),
      this.apiUsersService.getUsers(),
      this.apiPlansService.getPlans()
    ]).subscribe(
      ([specialistsData, usersData, plansData]) => {
        // Maneja la respuesta del servicio de especialistas
        console.log('Respuesta del servicio de especialistas:', specialistsData);
        this.specialists = specialistsData as specialist[];
        // Maneja la respuesta del servicio de usuarios
        console.log('Respuesta del servicio de usuarios:', usersData);
        this.users = usersData as user[];
        // Maneja la respuesta del servicio de planes
        console.log('Respuesta del servicio de planes:', plansData);
        this.plans = plansData as plan[];
      },
      (error) => {
        // Maneja los errores aquí
        console.error('Error al llamar a los servicios:', error);
      }
    );
  }
}
