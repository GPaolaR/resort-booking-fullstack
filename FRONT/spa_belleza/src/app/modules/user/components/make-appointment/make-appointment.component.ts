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
  }
  calculateTotalPrice(){
    if(this.selectedPlan?.pricePerPerson === 1){
      this.totalPrice = (this.selectedPlan?.discountedPrice ?? 0)*this.numPeople;
    }else{
      this.totalPrice = this.selectedPlan?.discountedPrice ?? 0;
    }
  }
  onButtonClick(){
    console.log('Membresia de usuario:', this.membershipTypeId);
    console.log('Plan Seleccionado:', this.selectedPlan);
    console.log('Especialidades Seleccionado:', this.selectedSpeciality);
    console.log('numero de personas Seleccionado:', this.numPeople);

    this.apiMakeAppointmentService.getStartDate(
      parseInt(this.membershipTypeId, 10),
      parseInt(this.selectedPlan.planId, 10)
    ).pipe(
      switchMap((startDateResponse: any) => {
        // Maneja la respuesta del servidor de getStartDate aquí
        console.log('Mensaje getStartDate:', startDateResponse);
        this.startDate = startDateResponse;

        if (this.selectedSpeciality.length < 2 && this.startDate) {
          const formattedDate = this.datePipe.transform(this.startDate, 'yyyy-MM-ddTHH:mm') ?? '';
          return this.apiMakeAppointmentService.getTimeSlotsSpeciality(
            parseInt(this.selectedSpeciality[0].specialityId, 10),
            formattedDate
          );
        } else {
          // Devuelve un observable vacío si no necesitas realizar más llamadas
          return of(null);
        }
      })
    ).subscribe(
      (timeSlotsResponse: any | null) => {
        if (timeSlotsResponse !== null) {
          // Maneja la respuesta del servidor de getTimeSlotsSpeciality aquí
          console.log('Mensaje getTimeSlotsSpeciality:', timeSlotsResponse);
          this.appointmentData = this.transformData(timeSlotsResponse);
          console.log('Datos de la cita:', this.appointmentData);
        }
      },
      (error: any) => {
        // Maneja los errores del servidor de getStartDate o getTimeSlotsSpeciality aquí
        const errorMessage = error.error.message || 'Ocurrió un error inesperado';
        this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
      }
    );
  }
 
  onButtonAgendaClick(){
    console.log(this.selectedDate) ;
  }


  transformData(data: any[]): any[] {
    // Transforma los datos según tu necesidad, por ejemplo, separando cada availableTimeSlots o matchingTimeSlots
    let transformedData: any[] = [];

    data.forEach(appointment => {
      if (appointment.availableTimeSlots) {
        appointment.availableTimeSlots.forEach((timeSlot: any) => {
          transformedData.push({
            specialistId: appointment.specialistId,
            specialistName: appointment.specialistName,
            specialistId1: null,
            specialistId2: null,
            date: appointment.date,
            timeSlot: timeSlot
          });
        });
      } else if (appointment.matchingTimeSlots) {
        appointment.matchingTimeSlots.forEach((timeSlot: any) => {
          transformedData.push({
            specialistId: null,
            specialistId1: appointment.specialistId1,
            specialistId2: appointment.specialistId2,
            date: appointment.date,
            timeSlot: timeSlot
          });
        });
      }
    });

    return transformedData;
  }
}
