import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiAgendaService {
  private apiUrl = 'http://localhost:8080/api'; 

  constructor(private http: HttpClient) { }
  
  cancelAppoinment(idAppointment: number): Observable<any> {
    console.log(idAppointment);
    let params = new HttpParams()
      .set('idAppointment', idAppointment);

    return this.http.put(`${this.apiUrl}/appointments/cancel?idAppointment=${idAppointment}`, { });
  }
}
