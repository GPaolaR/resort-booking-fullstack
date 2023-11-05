import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiSpecialistsService {

  private apiUrl = 'http://localhost:8080/api/specialist/list'; 

  constructor(private http: HttpClient) { }

  getSpecialists() {
    return this.http.get(`${this.apiUrl}`);
  }
}
