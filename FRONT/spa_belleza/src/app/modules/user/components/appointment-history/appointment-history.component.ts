import { Component } from '@angular/core';
import { appointment } from'../../../../shared/interfaces/appointment.interface';
import { ApiAppointmentsService } from '../../../../shared/services/api-appointments/api-appointments.service';
import { DatePipe } from '@angular/common';
import { filterAgenda } from'../../../../shared/interfaces/filterAgenda.interface';
import { Message, MessageService} from "primeng/api";
import { FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-appointment-history',
  templateUrl: './appointment-history.component.html',
  styleUrls: ['./appointment-history.component.scss']
})
export class AppointmentHistoryComponent {
  
    constructor( private apiAppointmentsService: ApiAppointmentsService,
      private datePipe: DatePipe,
      private messageService: MessageService) { }

    filterAgenda: filterAgenda | undefined;
    appointments!: any[];
    appointment!: appointment;
    userId: any;

  
    ngOnInit(): void {
      const startDate = new Date();
      const endDate = new Date(startDate);
      endDate.setFullYear(startDate.getFullYear() + 1);
      this.userId=localStorage.getItem('userId');

      this.filterAgenda = {
        specialistId: null,
        appointmentDate: null,
        userId: this.userId,
        planId: null,
        startDate: this.datePipe.transform(startDate, 'yyyy-MM-ddTHH:mm'),
        endDate: this.datePipe.transform(endDate, 'yyyy-MM-ddTHH:mm')
      };
    console.log(this.filterAgenda);
  
      this.apiAppointmentsService.getAgenda(this.filterAgenda).subscribe(
        (response: any) => {
          // Maneja la respuesta del servidor aquí
          console.log('Mensaje:', response);
          this.appointments=response;  
          if(this.appointments?.length==0){
            this.messageService.add({ severity: 'info', summary: 'Información', detail: 'No se han encontrado citas para el especialista en la fecha establecida' }); 
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
