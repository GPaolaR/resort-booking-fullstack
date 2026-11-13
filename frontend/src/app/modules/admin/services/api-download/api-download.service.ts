import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiDownloadService {
  private baseUrl = '/api/report/download-csv';

  constructor(private http: HttpClient) {}

  downloadCSV(startDate: string, endDate: string): Observable<Blob> {
    const url = `${this.baseUrl}`;
    const headers = new HttpHeaders({ 'Accept': 'text/csv' });
    let params = new HttpParams()
      .set('p_start_date', startDate)
      .set('p_end_date', endDate);

    return this.http.get(url, { headers, responseType: 'blob',  params: params });
  }

}
