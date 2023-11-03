import { Component, OnInit  } from '@angular/core';
import { ApiPlanService } from '../../services/api-plan/api-plan.service';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-select-plan',
  templateUrl: './select-plan.component.html',
  styleUrls: ['./select-plan.component.scss']
})
export class SelectPlanComponent {
  membershipTypeId='1';
  products: any[] = [];

  constructor(private apiPlanService: ApiPlanService,
    private router: Router,
    private route: ActivatedRoute
    ){}

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

  loginId: any;
  membershipType: any;
  userName: any;

  ngOnInit(): void {
    // this.route.queryParams.subscribe(params => {
    //   this.loginId = params['loginId'];
    //   localStorage.setItem('loginId', this.loginId)
               
    // });
    this.loginId=localStorage.getItem('loginId');
    //console.log(this.loginId);
    // Llama al método del servicio aquí
    this.apiPlanService.getUser(this.loginId).subscribe(
      (data:any) => {
        // Maneja la respuesta del servicio aquí
        console.log('Respuesta del servicio:', data);
        this.membershipTypeId=data.membershipType.membershipTypeId;
        this.membershipType=data.membershipType.membershipType;
        this.userName=data.userName;
        //console.log('this.membershipTypeId en getUser: ', this.membershipTypeId);
        localStorage.setItem('membershipType',this.membershipType);
        localStorage.setItem('name',this.userName);
        //console.log(localStorage.getItem('name'));
        //console.log(localStorage.getItem('membershipType'));

        // Llama al método del servicio aquí
        this.apiPlanService.getPlans(this.membershipTypeId).subscribe(
          (data:any) => {
            // Maneja la respuesta del servicio aquí
            //console.log('Respuesta del servicio:', data);
            this.products = data;

          },
          (error) => {
            // Maneja los errores aquí
            console.error('Error al llamar al servicio:', error);
          }
        );


      },
      (error) => {
        // Maneja los errores aquí
        console.error('Error al llamar al servicio:', error);
      }
    );
    
    //console.log('this.membershipTypeId: ', this.membershipTypeId);

    
  }

  onButtonClick(planType: string) {
    this.router.navigate(['user/appointment'], { queryParams: { planType: planType } });
  }
  
}