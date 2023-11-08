import { Pipe, PipeTransform } from '@angular/core';
import { DatePipe } from '@angular/common';

@Pipe({
  name: 'dateFormat'
})
export class DateFormatPipe implements PipeTransform {

  transform(value: string): string | null{
    const datePipe = new DatePipe('en-US');
    const date = new Date(value);
    return datePipe.transform(date, 'dd/MM/yyyy HH:mm', 'UTC');
  }
}