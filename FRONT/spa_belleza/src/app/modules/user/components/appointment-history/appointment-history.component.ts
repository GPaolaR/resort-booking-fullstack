import { Component } from '@angular/core';

@Component({
  selector: 'app-appointment-history',
  templateUrl: './appointment-history.component.html',
  styleUrls: ['./appointment-history.component.scss']
})
export class AppointmentHistoryComponent {
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

  // responsiveOptions = [
  //   {
  //       breakpoint: '1400px',
  //       numVisible: 4,
  //       numScroll: 4
  //   },
  //   {
  //       breakpoint: '1000px',
  //       numVisible: 3,
  //       numScroll: 3
  //   },
  //   {
  //     breakpoint: '700px',
  //     numVisible: 2,
  //     numScroll: 2
  // },
  //   {
  //       breakpoint: '520px',
  //       numVisible: 1,
  //       numScroll: 1
  //   }
  // ];  

}
