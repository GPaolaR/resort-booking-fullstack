import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiPlansService {

  private apiUrl = '/api/plans/list'; 

  constructor(private http: HttpClient) { }

  getPlans() {
    return this.http.get(`${this.apiUrl}`);
  }
}
