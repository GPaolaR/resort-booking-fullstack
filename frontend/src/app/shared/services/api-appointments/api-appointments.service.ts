import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { filterAgenda } from'../../interfaces/filterAgenda.interface';

@Injectable({
  providedIn: 'root'
})
export class ApiAppointmentsService {


  private apiUrl = '/api/appointments'; 

  constructor(private http: HttpClient) { }

  getAgenda(filterData: filterAgenda) {
    return this.http.post(`${this.apiUrl}/get-agenda`, filterData);
  }
}
