import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpEvent,
  HttpHandler,
  HttpRequest,
  HttpResponse,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor{
  constructor(private messageService: MessageService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((error: HttpEvent<any>) => {
        if (error instanceof HttpErrorResponse && error.status === 404) {
          const errorMessage = error.error.message || 'Recurso no encontrado';
          console.error('Error 404:', errorMessage);
          // Muestra el mensaje de error como un Toast de PrimeNG
          //this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
  
        }
        return throwError(error);
      })
    );
  }
}

/*import {catchError} from 'rxjs/internal/operators';
import {ErrorService} from '../my-services/error.service';

@Injectable({
  providedIn: 'root'
})
export class HttpErrorInterceptor implements HttpInterceptor {
  construct(private errorService: ErrorService) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler) : Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError(error => {
        let errorMessage = '';
        if (error instanceof ErrorEvent) {
          // client-side error
          errorMessage = `Client-side error: ${error.error.message}`;
        } else {
          // backend error
          errorMessage = `Server-side error: ${error.status} ${error.message}`;
        }
        
        // aquí podrías agregar código que muestre el error en alguna parte fija de la pantalla.
        this.errorService.show(errorMessage);
        return throwError(errorMessage);
      })
    );
  }
}*/
/*
@Injectable({
  providedIn: 'root'
})
export class AppInterceptor implements HttpInterceptor {

    constructor() {}
    console.log('servicioerror');

    intercept(request: HttpRequest<any>, next: HttpHandler ): Observable<HttpEvent<any>> {

      return next.handle(request).catch((error: HttpErrorResponse) => {

          // Si sabes que el servidor hace el handling de las "error responses"
          // es decir, si el servidor devuelve los errores como instancias de HttpErrorResponse
          if (error instanceof HttpErrorResponse) {
              if (error.status === 404) {
                console.error('An error occurred:', error.message);
              }

              if (error.status === 500) {
                  // otra acción distinta, por ejemplo...
              }
          }
      });
    }
}
*/