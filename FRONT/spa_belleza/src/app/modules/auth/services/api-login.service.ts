import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiLoginService {

  private apiUrl = 'http://localhost:8080'; 

  constructor(private http: HttpClient) { }

  validateUser(credentialUser: any) {
    return this.http.post(`${this.apiUrl}/api/login/validate`, credentialUser);
  }
}
