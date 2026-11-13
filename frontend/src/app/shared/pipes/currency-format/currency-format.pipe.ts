import { Pipe, PipeTransform } from '@angular/core';
import { CurrencyPipe } from '@angular/common';

@Pipe({
  name: 'currencyFormat'
})
export class CurrencyFormatPipe implements PipeTransform {

  transform(value: number): string | null{
    const currencyPipe = new CurrencyPipe('en-CO');
    return currencyPipe.transform(value, '$', 'symbol', '1.2-2' || '');
  }

}