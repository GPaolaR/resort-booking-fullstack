import { Component } from '@angular/core';

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

  date: Date | undefined;
  cities: City[] | undefined;

  selectedCity: City | undefined;

  ngOnInit() {
      this.cities = [
          { name: 'New York', code: 'NY' },
          { name: 'Rome', code: 'RM' },
          { name: 'London', code: 'LDN' },
          { name: 'Istanbul', code: 'IST' },
          { name: 'Paris', code: 'PRS' }
      ];
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
