import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiPlanService {

  private apiUrl = 'http://localhost:8080/api'; 

  constructor(private http: HttpClient) { }

  getPlans(membershipTypeId: String ) {
    return this.http.get(`${this.apiUrl}/plans/${membershipTypeId}`);
  }

  getUser(loginId: String ) {
    return this.http.get(`${this.apiUrl}/user/Us/${loginId}`);
  }
}
