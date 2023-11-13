import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { plan } from'../../../../shared/interfaces/plan.interface';
import { ApiPlanService } from '../../services/api-plan/api-plan.service';
//import { ApiPlansService } from '../../../../shared/services/api-plans/api-plans.service';
import { Message, MessageService} from "primeng/api";
import { speciality } from '../../interfaces/speciality.interface';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-make-appointment',
  templateUrl: './make-appointment.component.html',
  styleUrls: ['./make-appointment.component.scss']
})
export class MakeAppointmentComponent implements OnInit{

  constructor(private route: ActivatedRoute,
    private apiPlanService: ApiPlanService,
    private messageService: MessageService,
    private cdr: ChangeDetectorRef) {
      this.selectedPlan={planId: '', planType: '', planDescription: '', discountedPrice: '', pricePerPerson: ''}
    }

  date: Date | undefined;
  specialities!: speciality[];
  plans: plan[] |undefined = [];
  selectedPlan?:any;
  showInputNumber: boolean = false;
  numPeople: number = 1;
  selectedSpeciality!: speciality[];
  membershipTypeId='1';
  totalPrice:any;

  activeIndex: number = 0;
  products = [
    {
      name:'8:00'      
    },
    {
      name:'10:00'      
    },
    {
      name:'12:00'      
    },
    {
      name:'1:00'      
    },
  ]


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


    console.log('numero de personas Seleccionado:', this.numPeople);
    console.log('Plan Seleccionado:', this.selectedPlan);
    console.log('Especialidades Seleccionado:', this.selectedSpeciality);
    

  }
  

}






