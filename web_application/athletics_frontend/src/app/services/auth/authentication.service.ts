import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { of, Observable } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private urlEndpoint = environment.authenticationUrl;

  constructor(private http: HttpClient, private router: Router) { }

  logout() {}


  login(username: string, password: string) {

    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    });

    const body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);



    return this.http.post<any>(this.urlEndpoint, body.toString(), { headers })
          .pipe(map(res => {
            /* const utente: Utente = new Utente(jwtObj); */
            sessionStorage.setItem('loggedUser', 'loggedUser');
            alert('SIIIIIIIIIIIIIIII');
          }
        ));
    }
}
