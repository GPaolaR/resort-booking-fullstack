import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
interface City {
  name: string;
  code: string;
}
@Component({
  selector: 'app-make-appointment',
  templateUrl: './make-appointment.component.html',
  styleUrls: ['./make-appointment.component.scss']
})
export class MakeAppointmentComponent {
  date: Date | undefined;
  cities: City[] | undefined;

  selectedCity: City | undefined;
  products = [
    {
      name:'8:00'      
    },
    {
      name:'10:00'      
    },
  ]

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
        const planType = params['planType'];
        console.log(planType);
        // Haz algo con el valor de planType
    });
    this.cities = [
      { name: 'New York', code: 'NY' },
      { name: 'Rome', code: 'RM' },
      { name: 'London', code: 'LDN' },
      { name: 'Istanbul', code: 'IST' },
      { name: 'Paris', code: 'PRS' }
  ];
  }
 

}






