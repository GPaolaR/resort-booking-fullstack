import { Component } from '@angular/core';
import { ApiSpecialistsService } from '../../services/api-specialists/api-specialists.service';
import { specialist} from'../../interfaces/specialist.interface';
import { appointment } from'../../../../shared/interfaces/appointment.interface';
import { ApiAppointmentsService } from '../../../../shared/services/api-appointments/api-appointments.service';
import { DatePipe } from '@angular/common';
import { filterAgenda } from'../../../../shared/interfaces/filterAgenda.interface';
import { Message, MessageService} from "primeng/api";
import { FormBuilder, Validators} from "@angular/forms";

interface City {
  name: string;
  code: string;
}
@Component({
  selector: 'app-specialist-agenda',
  templateUrl: './specialist-agenda.component.html',
  styleUrls: ['./specialist-agenda.component.scss']
})
export class SpecialistAgendaComponent {
  filterAgenda: filterAgenda | undefined;
  appointments!: any[];
  appointment!: appointment;
  dateAppointment: Date | undefined;
  specialists: specialist[] | undefined;

  selectedSpecialist: specialist | undefined;

  constructor(private apiSpecialistsService: ApiSpecialistsService,
    private apiAppointmentsService: ApiAppointmentsService,
    private datePipe: DatePipe,
    private messageService: MessageService
    ){}
  ngOnInit() {
    this.apiSpecialistsService.getSpecialists().subscribe(
      (response: any) => {
        // Maneja la respuesta del servidor aquí        
        this.specialists=response;     
        if(this.specialists?.length===0){
          this.messageService.add({ severity: 'info', summary: 'Información', detail: 'No se han encontrado especialistas registrados' }); 
        }
      },
      (error: any) => {
        // Maneja los errores del servidor aquí
        const errorMessage = error.error.message || 'Ocurrió un error inesperado';
        this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
      }
    );
      
  }

  search(){
    // Validar que al menos un filtro ha sido ingresado
    if (!this.selectedSpecialist || !this.dateAppointment) {
      const errorMessage = "Por favor, ingrese los filtro.";
      console.log(errorMessage);
      this.messageService.add({ severity: 'warn', summary: 'Advertencia', detail: errorMessage });
      return;
    }
    this.filterAgenda = {
      specialistId: this.selectedSpecialist ? this.selectedSpecialist.specialistId : null,
      appointmentDate: this.dateAppointment ? this.datePipe.transform(this.dateAppointment, 'yyyy-MM-ddTHH:mm') : null,
      userId: null,
      planId: null,
      startDate: null,
      endDate: null
    };
  

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
    console.log('Especialista: ',this.selectedSpecialist);    
    console.log('FechaInicial: ',this.datePipe.transform(this.dateAppointment, 'yyyy-MM-ddTHH:mm'));
    
  }
  
  products = [
    {
      planType:'title',
      planDescription:'',
      
    },
    {
      planType:'title',
      planDescription:'',
      
    },
    {
      planType:'title',
      planDescription:'',
      
    },
    {
      planType:'title',
      planDescription:'',
      
    },
    {
      planType:'title',
      planDescription:'',
      
    }
  ];
}
