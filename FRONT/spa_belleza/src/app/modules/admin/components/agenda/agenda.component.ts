import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ApiSpecialistsService } from '../../services/api-specialists/api-specialists.service';
import { ApiAgendaService } from '../../services/api-agenda/api-agenda.service';
import { specialist} from'../../interfaces/specialist.interface';
import { plan } from'../../../../shared/interfaces/plan.interface';
import { user } from'../../interfaces/user.interface';
import { filterAgenda } from'../../../../shared/interfaces/filterAgenda.interface';
import { appointment } from'../../../../shared/interfaces/appointment.interface';
import { ApiUsersService } from '../../services/api-users/api-users.service';
import { ApiPlansService } from '../../../../shared/services/api-plans/api-plans.service';
import { ApiAppointmentsService } from '../../../../shared/services/api-appointments/api-appointments.service';
import { forkJoin } from 'rxjs';
import { DatePipe } from '@angular/common';
import { Message, MessageService} from "primeng/api";


@Component({
  selector: 'app-agenda',
  templateUrl: './agenda.component.html',
  styleUrls: ['./agenda.component.scss']
})
export class AgendaComponent {
  filterAgenda: filterAgenda | undefined;
  appointments!: appointment[];
  appointment!: appointment;
  
  specialists: specialist[] | undefined;
  users: user[] | undefined;
  plans: plan[] | undefined;
  inicialDate: Date | undefined;
  endDate: Date | undefined;

  selectedSpecialist: specialist | undefined;
  selectedUser: user | undefined;
  selectedPlan: plan | undefined;

  constructor(private cdr: ChangeDetectorRef,
    private apiSpecialistsService: ApiSpecialistsService,
    private apiAgendaService: ApiAgendaService,
    private apiAppointmentsService: ApiAppointmentsService,
    private apiUsersService: ApiUsersService,
    private apiPlansService: ApiPlansService,
    private datePipe: DatePipe,
    private messageService: MessageService
    ){}


  ngOnInit() {
     // Combina las observables de los servicios en una sola
    forkJoin([
      this.apiSpecialistsService.getSpecialists(),
      this.apiUsersService.getUsers(),
      this.apiPlansService.getPlans()
    ]).subscribe(
      ([specialistsData, usersData, plansData]) => {
        // Respuesta del servicio de especialistas
        this.specialists = specialistsData as specialist[];
        // Respuesta del servicio de usuarios
        this.users = usersData as user[];
        // Respuesta del servicio de planes
        this.plans = plansData as plan[];
      },
      (error) => {
        // Maneja los errores aquí
        console.error('Error al llamar a los servicios:', error);
      }
    );
  }

  search(){
    // Validar que al menos un filtro ha sido ingresado
    if (!this.selectedSpecialist && !this.selectedUser && !this.selectedPlan && !this.inicialDate && !this.endDate) {
      const errorMessage = "Por favor, ingrese al menos un filtro.";
      this.messageService.add({ severity: 'warn', summary: 'Advertencia', detail: errorMessage });
      return;
    }
    // Validar rango de fechas
    if (this.inicialDate && this.endDate) {
      const MS_PER_DAY = 24 * 60 * 60 * 1000; // Cantidad de milisegundos en un día
      const diffInDays = Math.round(Math.abs((this.inicialDate.getTime() - this.endDate.getTime()) / MS_PER_DAY));

      // Validar que la diferencia en días sea menor o igual a 90 (3 meses)
      if (diffInDays > 90) {
        const errorMessage = "El rango de fechas no puede ser mayor a tres meses.";
        this.messageService.add({ severity: 'warn', summary: 'Advertencia', detail: errorMessage });
        return;
      }
      // Validar que la fecha de inicio sea menor a la fecha de fin
      if (this.inicialDate > this.endDate) {
        const errorMessage = "La fecha de inicio debe ser menor a la fecha de fin.";
        this.messageService.add({ severity: 'warn', summary: 'Advertencia', detail: errorMessage });
        return;
      }
    }
    this.filterAgenda = {
      specialistId: this.selectedSpecialist ? this.selectedSpecialist.specialistId : null,
      appointmentDate: null,
      userId: this.selectedUser ? this.selectedUser.userId : null,
      planId: this.selectedPlan ? this.selectedPlan.planId : null,
      startDate: this.inicialDate ? this.datePipe.transform(this.inicialDate, 'yyyy-MM-ddTHH:mm') : null,
      endDate: this.endDate ? this.datePipe.transform(this.endDate, 'yyyy-MM-ddTHH:mm') : null
    };
  

    this.apiAppointmentsService.getAgenda(this.filterAgenda).subscribe(
      (response: any) => {
        // Maneja la respuesta del servidor aquí
        console.log('Mensaje:', response);
        this.appointments=response;
        if(this.appointments?.length==0){
          this.messageService.add({ severity: 'info', summary: 'Información', detail: 'No se han encontrado citas para los parámetros establecidos' }); 
        }
        
      },
      (error: any) => {
        // Maneja los errores del servidor aquí
        const errorMessage = error.error.message || 'Ocurrió un error inesperado';
        this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
      }
    );
    // console.log('Especialista: ',this.selectedSpecialist);
    // console.log('Usuario: ',this.selectedUser);
    // console.log('Plan: ',this.selectedPlan?.planId);
    // console.log('FechaInicial: ',this.datePipe.transform(this.inicialDate, 'yyyy-MM-ddTHH:mm'));
    // console.log('FechaFin: ',this.endDate);
  }

  editProduct(appointment: appointment) {
    this.appointment = { ...appointment };
    //this.productDialog = true;
}

deleteProduct(appointment: appointment) {
  console.log('appointment: ',appointment);

  this.apiAgendaService.cancelAppoinment(appointment?.appointmentId!).subscribe(
    (response: any) => {
      // Maneja la respuesta del servidor aquí
      console.log('Mensaje:', response);
      //this.appointments=response;
      this.messageService.add({ severity: response.status, summary: 'Error', detail: response.message });
      
      
    },
    (error: any) => {
      // Maneja los errores del servidor aquí
      const errorMessage = error.error.message || 'Ocurrió un error inesperado';
      this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
    }
  );
    // this.confirmationService.confirm({
    //     message: 'Are you sure you want to delete ' + appointment.name + '?',
    //     header: 'Confirm',
    //     icon: 'pi pi-exclamation-triangle',
    //     accept: () => {
    //         this.products = this.products.filter((val) => val.id !== product.id);
    //         this.product = {};
    //         this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Deleted', life: 3000 });
    //     }
    // });
}
}
