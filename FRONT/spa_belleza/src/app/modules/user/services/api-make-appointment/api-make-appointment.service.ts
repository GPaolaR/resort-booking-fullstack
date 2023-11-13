import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiMakeAppointmentService {

  private apiUrl = 'http://localhost:8080/api'; 

  constructor(private http: HttpClient) { }

  getStartDate(membershipTypeId: number, planId: number): Observable<any> {
    // Construir los parámetros usando HttpParams
    let params = new HttpParams()
      .set('membershipTypeId', membershipTypeId)
      .set('planId', planId);

    // Realizar la solicitud GET con los parámetros
    return this.http.get(`${this.apiUrl}/appointments/calculate-start-date`, { params: params });;
  }

  getTimeSlotsSpeciality(specialityId: number, startDate: string): Observable<any> {
    // Construir los parámetros usando HttpParams
    let params = new HttpParams()
      .set('specialityId', specialityId)
      .set('startDate', startDate);

    // Realizar la solicitud GET con los parámetros
    return this.http.get(`${this.apiUrl}/appointments/available-time-slots-by-speciality`, { params: params });;
  }

}
