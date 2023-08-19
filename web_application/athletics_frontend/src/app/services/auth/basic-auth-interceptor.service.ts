import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthInterceptor implements HttpInterceptor {

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let currentUser = sessionStorage.getItem('loggedUser');
    let session = sessionStorage.getItem('token');
    if (currentUser && session) {
      session = 'Bearer ' + session;
      const cloned = request.clone({
          withCredentials: true,
          setHeaders: {
          'Content-Type': 'application/json',
          'Authorization' : session}
        });
      return next.handle(cloned);
      } else {
      return next.handle(request);
    }
  }
}
