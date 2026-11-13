import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiLoginService {

  private apiUrl = '/api'; 

  constructor(private http: HttpClient) { }

  validateUser(credentialUser: any) {
    return this.http.post(`${this.apiUrl}/login/validate`, credentialUser);
  }
  
  getUser(loginId: String ) {
    return this.http.get(`${this.apiUrl}/user/us/${loginId}`);
  }
  getAdmin(loginId: String ) {
    return this.http.get(`${this.apiUrl}/admin/us/${loginId}`);
  }
}
