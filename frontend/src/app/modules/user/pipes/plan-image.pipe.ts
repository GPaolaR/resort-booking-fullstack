import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'planImage'
})
export class PlanImagePipe implements PipeTransform {

  transform(planType: string): string {
     switch (planType) {
      case 'Spa para parejas':
        return 'couples.jpg';
      case 'Spa para empresas':
        return 'companies.jpg';
      case 'Masajes de relajación':
        return 'relax.jpg';
      case 'Tratamientos faciales':
        return 'facial.jpg';
      default:
        return 'relax.jpg';
    }
  }

}
