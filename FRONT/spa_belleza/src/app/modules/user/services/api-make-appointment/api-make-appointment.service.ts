import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiMakeAppointmentService {

  private apiUrl = 'http://localhost:8080/api'; 

  constructor(private http: HttpClient) { }

  getPlans(specialityId: number, startDate: number): Observable<any> {
    // Construir los parámetros usando HttpParams
    let params = new HttpParams()
      .set('specialityId', specialityId)
      .set('parametro2', startDate.toString());

    // Realizar la solicitud GET con los parámetros
    return this.http.get('tu/url/api', { params: params });;
  }
}
