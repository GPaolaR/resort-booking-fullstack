import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    // Verifica si el usuario está autenticado o tiene alguna lógica de autenticación
    // Puedes ajustar esto según tus necesidades
    const isAuthenticated = /* Tu lógica de autenticación */ true;

    if (!isAuthenticated) {
      // Si no está autenticado, redirige a la página de inicio de sesión
      this.router.navigate(['/auth/login']);
      return false;
    }

    return true;
  }
}
