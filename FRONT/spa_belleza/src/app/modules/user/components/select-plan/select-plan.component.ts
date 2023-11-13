import { Component, OnInit  } from '@angular/core';
import { ApiPlanService } from '../../services/api-plan/api-plan.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Message, MessageService} from "primeng/api";

@Component({
  selector: 'app-select-plan',
  templateUrl: './select-plan.component.html',
  styleUrls: ['./select-plan.component.scss']
})
export class SelectPlanComponent implements OnInit{
  constructor(private apiPlanService: ApiPlanService,
    private messageService: MessageService,
    private router: Router,
    private route: ActivatedRoute
    ){}
    
  plans: any[] = [];
  membershipTypeId='1';
  responsiveOptions = [
    {
        breakpoint: '1400px',
        numVisible: 4,
        numScroll: 4
    },
    {
        breakpoint: '1000px',
        numVisible: 3,
        numScroll: 3
    },
    {
      breakpoint: '700px',
      numVisible: 2,
      numScroll: 2
  },
    {
        breakpoint: '520px',
        numVisible: 1,
        numScroll: 1
    }
  ];  

  ngOnInit(): void {  
    this.membershipTypeId = localStorage.getItem('membershipTypeId')!;

    this.apiPlanService.getPlans(this.membershipTypeId).subscribe(
      (data:any) => {
        localStorage.setItem('plans',JSON.stringify(data));
        this.plans = data;
      },
      (error) => {
        const errorMessage = error.error.message || 'Ocurrió un error inesperado';
        this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
      }
    );
  }

  onButtonClick(plan: any[]) {
    localStorage.setItem('planSelected', JSON.stringify(plan));
    this.router.navigate(['user/appointment']);
  }
  
}