import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiSpecialistsService {

  private apiUrl = '/api/specialist/list'; 

  constructor(private http: HttpClient) { }

  getSpecialists() {
    return this.http.get(`${this.apiUrl}`);
  }
}
