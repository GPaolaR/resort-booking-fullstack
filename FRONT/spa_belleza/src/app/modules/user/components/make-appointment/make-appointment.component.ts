import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { plan } from'../../../../shared/interfaces/plan.interface';
import { ApiPlansService } from '../../../../shared/services/api-plans/api-plans.service';
import { Message, MessageService} from "primeng/api";
import { speciality } from '../../interfaces/speciality.interface';

@Component({
  selector: 'app-make-appointment',
  templateUrl: './make-appointment.component.html',
  styleUrls: ['./make-appointment.component.scss']
})
export class MakeAppointmentComponent {
  date: Date | undefined;
  //cities: City[] | undefined;
  specialities!: speciality[];
  plans: plan[] | undefined;
  selectedPlan: plan | undefined;
  showInputNumber: boolean = false;
  numPeople: number = 2;

  selectedSpeciality!: speciality[];
  //selectedCity: City | undefined;

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

  constructor(private route: ActivatedRoute,
    private apiPlansService: ApiPlansService,
    private messageService: MessageService) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.selectedPlan = params['planType'];
      //console.log(this.selectedPlan);
    });
    this.apiPlansService.getPlans().subscribe(
      (response: any) => {
        // Maneja la respuesta del servidor aquí
        //console.log('Mensaje:', response);
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
    
    this.specialities = [
      { specialityName: 'Facial', specialityId: '1' },
      { specialityName: 'Corporal', specialityId: '2' },
      { specialityName: 'Uñas', specialityId: '3' }
  ];
  }

  onPlanSelectionChange() {
    this.showInputNumber = (this.selectedPlan?.planType == 'Spa para empresas') ?? false;
  }
 

}






