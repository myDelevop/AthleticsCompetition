import { AuthUser } from './../model/authUser';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthGroup } from '../model/authGroup';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  private urlEndpoint = environment.userServiceUrl;
  dialogData: any;

  constructor(private http: HttpClient, private toastr: ToastrService) { }

  getDialogData() {
    return this.dialogData;
  }

  getUsers(): Observable<AuthUser[]> {
    const url = `${this.urlEndpoint}/getUsers`;
    return this.http.get<AuthUser[]>(url).pipe(
        // do something if you want
        catchError(this.handleError<AuthUser[]>('getUsers', [])));
  }

  /*
  getUserNo404<Data>(id: number): Observable<User> {
    const url = `${this.urlEndpoint}/?id=${id}`;
    return this.http.get<User[]>(url).pipe(
      map(users => users[0]), tap(h => {
        const outcome = h ? 'fetched' : 'did not find';
        // this.log('${outcome} user id=${id}');
      }), catchError(this.handleError<User>('getUser id=${id}')));
  }
*/

  getUserById(user: AuthUser): Observable<AuthUser> {
    const url = `${this.urlEndpoint}/getUserById/${user.idUser}`;
    return this.http.get<AuthUser>(url).pipe(
      // do something if you want
      catchError(this.handleError<AuthUser>('getUser id=${id}')));
  }

  addUser(user: AuthUser): Observable<AuthUser> {
    this.dialogData = user;
    const url = `${this.urlEndpoint}/insertUser`;

    return this.http.post<AuthUser>(url, user, httpOptions).pipe(
      catchError(this.handleError<AuthUser>('addUser')));
  }

  deleteUser(user: AuthUser): Observable<AuthUser> {
    const url = `${this.urlEndpoint}/deleteUser/${user.idUser}`;

    return this.http.delete(url, httpOptions).pipe(
      catchError(this.handleError<any>('deleteUser')));
  }

  updateUser(user: AuthUser): Observable<AuthUser> {
    this.dialogData = user;
    const url = `${this.urlEndpoint}/updateUser`;

    return this.http.put(url, user, httpOptions).pipe(
      catchError(this.handleError<any>('updateUser'))
    );
  }

  /*
  searchUsers(term: string): Observable<User[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<User[]>(`${this.gestioneUtentiUrl}/?name=${term}`).pipe(
      // do something if you want
      catchError(this.handleError<User[]>('Users', [])));
  }
  */

  getGroupsByUser(user: AuthUser): Observable<AuthGroup> {
    const url = `${this.urlEndpoint}/getGroups/${user.idUser}`;
    return this.http.get<AuthGroup>(url).pipe(
      catchError(this.handleError<AuthGroup>('getGroup id=${id}')));
  }



  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error('Error occurred. Details: ' + error.name + ' ' + error.message);
      this.toastr.error('Si è verificato un errore, controlla la connessione ', '', {timeOut: 3000});

      return of(result as T);
    };
  }
}
