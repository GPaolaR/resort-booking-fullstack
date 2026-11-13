import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { HttpClient } from '@angular/common/http';
import { ApiUploadService } from '../../services/api-upload/api-upload.service';
import { ApiDownloadService } from '../../services/api-download/api-download.service';
import { DatePipe } from '@angular/common';

interface UploadEvent {
  originalEvent: Event;
  files: File[];
}
@Component({
  selector: 'app-management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.scss']
})
export class ManagementComponent {
  startDate: Date | undefined;
  endDate: Date | undefined;
  selectedFile: File | null = null;

  constructor(private http: HttpClient,
    private messageService: MessageService,
    private datePipe: DatePipe,
    private apiDownloadService: ApiDownloadService) {}

  onFileSelected(event: any) {
    // Obtener el archivo seleccionado del evento de cambio
    this.selectedFile = event.target.files[0];
  }

  uploadFile() {
    // Verificar si se seleccionó un archivo
    if (!this.selectedFile) {
      console.log('Seleccione un archivo antes de hacer clic en "Cargar Archivo".');

      this.messageService.add({ severity: 'warn', summary: 'Advertencia', detail: "Seleccione un archivo antes de hacer clic en 'Cargar Archivo'." });
      return;
    }

    // Crear un objeto FormData y agregar el archivo
    const formData: FormData = new FormData();
    formData.append('file', this.selectedFile);

    this.http.post('/api/appointments/upload', formData).subscribe(
      (response: any) => {
        console.log(response);
        console.log('Archivo cargado con éxito.');
        this.messageService.add({ severity: 'success', summary: 'Archivo cargado', detail: response.message });
        return;
      },
      (error: any) => {
        console.log(error);
        this.messageService.add({ severity: 'error', summary: 'Error', detail: error.error.message });
      }
    );
  }

  downloadFile(): void {
    // Validar que al menos un filtro ha sido ingresado
    if (!this.startDate || !this.endDate) {
      const errorMessage = "Por favor, ingresar un rango de fechas.";
      this.messageService.add({ severity: 'warn', summary: 'Advertencia', detail: errorMessage });
      return;
    }

    // Validar rango de fechas
    if (this.startDate && this.endDate) {
      const MS_PER_DAY = 24 * 60 * 60 * 1000; // Cantidad de milisegundos en un día
      const diffInDays = Math.round(Math.abs((this.startDate.getTime() - this.endDate.getTime()) / MS_PER_DAY));
      
      // Validar que la diferencia en días sea menor o igual a 90 (3 meses)
      if (diffInDays > 90) {
        const errorMessage = "El rango de fechas no puede ser mayor a tres meses.";
        this.messageService.add({ severity: 'warn', summary: 'Advertencia', detail: errorMessage });
        return;
      }
      // Validar que la fecha de inicio sea menor a la fecha de fin
      if (this.startDate > this.endDate) {
        const errorMessage = "La fecha de inicio debe ser menor a la fecha de fin.";
        this.messageService.add({ severity: 'warn', summary: 'Advertencia', detail: errorMessage });
        return;
      }
    }


    const formattedStartDate = this.datePipe.transform(this.startDate, 'yyyy-MM-ddTHH:mm') ?? '';
    console.log('formattedStartDate', formattedStartDate);
    const formattedEndDate = this.datePipe.transform(this.endDate, 'yyyy-MM-ddTHH:mm') ?? '';
    console.log('formattedEndDate', formattedEndDate);
      this.apiDownloadService.downloadCSV(formattedStartDate,formattedEndDate).subscribe(
        (data: Blob) => {
          const blob = new Blob([data], { type: 'text/csv' });
          const url = window.URL.createObjectURL(blob);
          const a = document.createElement('a');
          a.href = url;
          a.download = 'archivo.csv';
          document.body.appendChild(a);
          a.click();
          document.body.removeChild(a);
        },
        error => {
          console.error('Error al descargar el archivo CSV', error);
        }
      );
    }
  
  }


  // url: any;
  // constructor(private messageService: MessageService,
  //   private apiUploadService:ApiUploadService) {}

  // uploadFile(event: any) {
  //   if (
  //     event &&
  //     event.target &&
  //     event.target.files &&
  //     event.target.files.length > 0
  //   ) {
  //     const file = event.target.files[0];
  //     if (file) {
  //       const fileSizeInMB = file.size / 1024 / 1024; // Size in MB

  //       if (fileSizeInMB > 5) {
  //         // The file exceeds the maximum size of 5 MB
  //         this.messageService.clear(); // Clear any messages
  //         this.messageService.add({
  //           severity: 'error',
  //           summary: 'Error',
  //           detail: 'El archivo no puede superar 5 MB',
  //           sticky: true, 
  //         });
  //       } else {
  //         const formData = new FormData();
  //         formData.append('file', file);

  //         this.apiUploadService.uploadFile(formData).subscribe(
  //           (res) => {
  //             console.log('Respuesta mediafile', res);
  //             this.url = res.url;
  //             this.messageService.add({
  //               severity: 'info',
  //               summary: 'Archivo Cargado',
  //               detail: 'Archivo cargado con éxito',
  //               sticky: true,
  //             });
  //             // this.formGroup.value.case.support = this.url;
  //           },
  //           (err) => {
  //             console.log(err);
  //           }
  //         );
  //       }
  //     }
  //   }
  // }
//}
