import { Injectable } from '@angular/core';
/*import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router
} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';
import { JwtHelperService } from '@auth0/angular-jwt';*/

@Injectable({
  providedIn: 'root'
})
export class AuthorizationGuard /*implements CanActivate */{
  /*constructor(
    private authService: AuthenticationService,
    private router: Router
  ) {}*/

  /*canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    if (this.authService.isLoggedIn()) {
      const helperJWT = new JwtHelperService();
      const roles: Array<string> = helperJWT.decodeToken(
        sessionStorage.getItem('token')
      ).roles;
      let ret = false;

      // Caso di dopo login
      if (next.url[0] == null) {
        return true;
      }
      switch (next.url[0].path) {
        case '': {
          if (roles.includes('ROLE_ADMIN')) {
            ret = true;
        }
          break;
        }
        case 'login': {
          ret = false;
          break;
        }
        case 'app-staz-forestali-controllori': {
          if (roles.includes('ROLE_ADMIN')) {
            ret = true;
          }
          break;
        }
        case 'app-utenti-gruppi': {
          if (roles.includes('ROLE_ADMIN')) {
            ret = true;
          }
          break;
        }
        case 'app-regole-checklist': {
          if (roles.includes('ROLE_ADMIN')) {
            ret = true;
          }
          break;
        }
        case 'app-gestione-template': {
          if (roles.includes('ROLE_ADMIN')) {
            ret = true;
          }
          break;
        }
        case 'assegnamento-controllori': {
          if (roles.includes('ROLE_ADMIN')) {
            ret = true;
          }
          break;
        }
        case 'app-anagrafiche': {
          if (roles.includes('ROLE_ADMIN')) {
            ret = true;
          }
          break;
        }
      }
      return ret;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }*/
}
