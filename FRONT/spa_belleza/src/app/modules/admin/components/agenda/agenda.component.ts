import { Component } from '@angular/core';
import { ApiSpecialistsService } from '../../services/api-specialists/api-specialists.service';
interface City {
  name: string;
  code: string;
}
interface specialist {
  specialistId: string;
  specialistFullName: string;
}
@Component({
  selector: 'app-agenda',
  templateUrl: './agenda.component.html',
  styleUrls: ['./agenda.component.scss']
})
export class AgendaComponent {
  date: Date | undefined;
  cities: City[] | undefined;
  specialists: specialist[] | undefined;

  selectedCity: City | undefined;
  selectedspecialist: specialist | undefined;

  constructor(private apiSpecialistsService: ApiSpecialistsService){}


  ngOnInit() {
    this.apiSpecialistsService.getSpecialists().subscribe(
      (data:any) => {
        // Maneja la respuesta del servicio aquí
        console.log('Respuesta del servicio:', data);
        this.specialists = data;

      },
      (error) => {
        // Maneja los errores aquí
        console.error('Error al llamar al servicio:', error);
      }
    );

      this.cities = [
          { name: 'New York', code: 'NY' },
          { name: 'Rome', code: 'RM' },
          { name: 'London', code: 'LDN' },
          { name: 'Istanbul', code: 'IST' },
          { name: 'Paris', code: 'PRS' }
      ];
  }
}
