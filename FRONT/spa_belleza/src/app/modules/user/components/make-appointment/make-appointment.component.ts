import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { plan } from'../../../../shared/interfaces/plan.interface';
import { ApiPlanService } from '../../services/api-plan/api-plan.service';
import { ApiMakeAppointmentService } from '../../services/api-make-appointment/api-make-appointment.service';
//import { ApiPlansService } from '../../../../shared/services/api-plans/api-plans.service';
import { Message, MessageService} from "primeng/api";
import { speciality } from '../../interfaces/speciality.interface';
import { ChangeDetectorRef } from '@angular/core';
import { DatePipe } from '@angular/common';
import { switchMap, catchError } from 'rxjs/operators';
import { of, EMPTY } from 'rxjs';

@Component({
  selector: 'app-make-appointment',
  templateUrl: './make-appointment.component.html',
  styleUrls: ['./make-appointment.component.scss']
})
export class MakeAppointmentComponent implements OnInit{

  constructor(private route: ActivatedRoute,
    private apiPlanService: ApiPlanService,
    private apiMakeAppointmentService: ApiMakeAppointmentService,
    private messageService: MessageService,
    private datePipe: DatePipe,
    private cdr: ChangeDetectorRef) {
      this.selectedPlan={planId: '', planType: '', planDescription: '', discountedPrice: '', pricePerPerson: ''}
    }

  startDate: string | undefined;
  specialities!: speciality[];
  plans: plan[] |undefined = [];
  selectedPlan?:any;
  showInputNumber: boolean = false;
  numPeople: number = 1;
  selectedSpeciality!: speciality[];
  membershipTypeId='1';
  totalPrice:any;
  OnButton: boolean = false;
  appointmentData: any[] = [];

  groupedData: any[] = [];
  selectedDate!: any;
  showButtonSearch = false;
  showButtonAgenda = false;
  showTable = false;
  
  ngOnInit() {
    this.membershipTypeId=localStorage.getItem("membershipTypeId")!;
    this.selectedPlan = JSON.parse(localStorage.getItem('planSelected')!)?? {planId: '', planType: '', planDescription: '', discountedPrice: '', pricePerPerson: ''};
    this.showInputNumber = (this.selectedPlan?.planType == 'Spa para empresas') ?? false
    this.cdr.detectChanges();    

    this.plans = JSON.parse(localStorage.getItem('plans')!)??[];
    if(this.plans?.length==0){
      this.apiPlanService.getPlans(this.membershipTypeId).subscribe(
        (response: any) => {
          // Maneja la respuesta del servidor aquí
          console.log('Mensaje getPlans:', response);
          this.plans =response;
          if(this.plans?.length==0){
            this.messageService.add({ severity: 'info', summary: 'Información', detail: 'No se han encontrado planes' }); 
          }          
        },
        (error: any) => {
          // Maneja los errores del servidor aquí
          const errorMessage = error.error.message || 'Ocurrió un error inesperado';
          this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
        }
      );
      console.log('No se han encontrado planes');
      this.messageService.add({ severity: 'info', summary: 'Información', detail: 'No se han encontrado planes' }); 
    }
    this.calculateTotalPrice();
    this.specialities = [
      { specialityName: 'Facial', specialityId: '1' },
      { specialityName: 'Corporal', specialityId: '2' },
      { specialityName: 'Uñas', specialityId: '3' }
  ];
  }

  onPlanSelectionChange() {   
    this.showInputNumber = (this.selectedPlan?.planType == 'Spa para empresas') ?? false;
    this.calculateTotalPrice(); 
    this.showButtonSearch = true;
  }
  onSpecialitySelectionChange() {
    this.showButtonAgenda = false; 
    this.showButtonSearch = true;
    this.showTable = false; 
  }
  calculateTotalPrice(){
    if(this.selectedPlan?.pricePerPerson === 1){
      this.totalPrice = (this.selectedPlan?.discountedPrice ?? 0)*this.numPeople;
    }else{
      this.totalPrice = this.selectedPlan?.discountedPrice ?? 0;
    }
  }
  onButtonClick(){
    this.apiMakeAppointmentService.getStartDate(
      parseInt(this.membershipTypeId, 10),
      parseInt(this.selectedPlan.planId, 10)
    ).pipe(
      switchMap((startDateResponse: any) => {
        this.startDate = startDateResponse;

        if (this.startDate) {
          const formattedDate = this.datePipe.transform(this.startDate, 'yyyy-MM-ddTHH:mm') ?? '';
          const formattedSpecialities = this.selectedSpeciality.map(speciality => speciality.specialityId).join(',');
          //console.log('Especialidades Seleccionado:', formattedSpecialities);
          return this.apiMakeAppointmentService.getTimeSlotsSpecialities(
            formattedSpecialities,
            formattedDate
          );
        } else {
          // Devuelve un observable vacío 
          return of(null);
        }
      })
    ).subscribe(
      (timeSlotsResponse: any | null) => {
        if (timeSlotsResponse !== null && timeSlotsResponse.length > 0) {
          this.appointmentData = this.transformData(timeSlotsResponse);
          this.showButtonAgenda = true;
        } else {
          // Mostrar mensaje de advertencia ya que timeSlotsResponse es null o vacío
          this.messageService.add({ severity: 'warn', summary: 'Advertencia', detail: 'No hay agenda disponibles en este momento.' });
          
        }
      },
      (error: any) => {
        // Errores del servidor de getStartDate o getTimeSlotsSpeciality
        const errorMessage = error.error.message || 'Ocurrió un error inesperado';
        this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
      }
    );
    this.showTable = true;
  }
 
  onButtonAgendaClick(){      

    console.log('Membresia de usuario:', this.membershipTypeId);
    console.log('Plan Seleccionado:', this.selectedPlan);
    console.log('Especialidades Seleccionado:', this.selectedSpeciality);
    console.log('numero de personas Seleccionado:', this.numPeople);
    this.showButtonAgenda = false;
  }


  transformData(data: any[]): any[] {
    // Transforma los datos separando cada timeSlot del matchingTimeSlots
    let transformedData: any[] = [];

    data.forEach(appointment => {
      if (appointment.matchingTimeSlots) {
        appointment.matchingTimeSlots.forEach((timeSlot: any) => {
          transformedData.push({
            specialistId1: appointment.specialistId1,
            specialistName1: appointment.specialistName1,
            specialistId2: appointment.specialistId2,
            specialistName2: appointment.specialistName2,            
            date: appointment.date,
            timeSlot: timeSlot
          });
        });
      } 
    });

    return transformedData;
  }
}
