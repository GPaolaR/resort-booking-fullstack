import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiUploadService {
  
  constructor(private http: HttpClient) { }

  uploadFile(formData: FormData): Observable<any> {
//     this.http.post('http://localhost:8080/api/appointments/upload', formData).subscribe(
//       (response: any) => {
//         alert('Archivo cargado con éxito.');
//       },
//       (error: any) => {
//         console.log(error);

//       }
//     );

    return this.http.post('localhost:8080/api/appointments/upload', formData).pipe(
      catchError((error) => {
        // Manejar el mensaje de error y mostrarlo al usuario
        const errorMessage = error.error.error; // Suponiendo que el servidor devuelve { "error": "Mensaje de error" }
        // Puedes mostrar el mensaje de error en una ventana emergente, en la consola o de la forma que prefieras
        console.error(errorMessage);
        //this.messageService.add({severity:'error', summary:'Error', detail:'Usuario o contraseña incorrectos'});
        return throwError(errorMessage);
       // TODO:Verificar
      })
    );
    
  }
}
