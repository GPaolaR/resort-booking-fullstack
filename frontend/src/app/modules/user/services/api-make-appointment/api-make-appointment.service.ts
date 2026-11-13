import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { appointmentData } from './../../../../shared/interfaces/appointmentData.interface';

@Injectable({
  providedIn: 'root'
})
export class ApiMakeAppointmentService {

  private apiUrl = '/api'; 

  constructor(private http: HttpClient) { }

  getStartDate(membershipTypeId: number, planId: number): Observable<any> {
    // Construir los parámetros usando HttpParams
    let params = new HttpParams()
      .set('membershipTypeId', membershipTypeId)
      .set('planId', planId);

    // Realizar la solicitud GET con los parámetros
    return this.http.get(`${this.apiUrl}/appointments/calculate-start-date`, { params: params });;
  }
  getTimeSlotsSpecialities(specialityIds: string, startDate: string): Observable<any> {
    // Construir los parámetros usando HttpParams
    let params = new HttpParams()
      .set('specialityId', specialityIds)
      .set('startDate', startDate);

    // Realizar la solicitud GET con los parámetros
    return this.http.get(`${this.apiUrl}/appointments/available-time-slots`, { params: params });;
  }

  insertAppointment(appointmentData: appointmentData) {
    return this.http.post(`${this.apiUrl}/appointments/insert`, appointmentData);
  }



}
