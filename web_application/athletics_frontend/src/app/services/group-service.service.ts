import { AuthUser } from './../model/authUser';
import { AuthGroup } from './../model/authGroup';
import { Observable, of } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { environment } from 'src/environments/environment';
import { Injectable, AbstractType } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class GroupServiceService {
  private urlEndpoint = environment.groupServiceUrl;
  dialogData: any;

  constructor(private http: HttpClient, private toastr: ToastrService) { }

  getDialogData() {
    return this.dialogData;
  }

  getGroups(): Observable<AuthGroup[]> {
    const url = `${this.urlEndpoint}/getGroups`;
    return this.http.get<AuthGroup[]>(url).pipe(
        catchError(this.handleError<AuthGroup[]>('getGroups', [])));
  }

  getGroupById(group: AuthGroup): Observable<AuthGroup> {
    const url = `${this.urlEndpoint}/getGroupById/${group.idGroup}`;
    return this.http.get<AuthGroup>(url).pipe(
      catchError(this.handleError<AuthGroup>('getGroup id=${id}')));
  }

  addGroup(group: AuthGroup): Observable<AuthGroup> {
    this.dialogData = group;
    const url = `${this.urlEndpoint}/insertGroup`;

    return this.http.post<AuthGroup>(url, group, httpOptions).pipe(
      catchError(this.handleError<AuthGroup>('addGruop')));
  }

  deleteGroup(group: AuthGroup): Observable<AuthGroup> {
    const url = `${this.urlEndpoint}/deleteGroup/${group.idGroup}`;

    return this.http.delete(url, httpOptions).pipe(
      catchError(this.handleError<any>('deleteGroup')));
  }

  updateGroup(group: AuthGroup): Observable<AuthGroup> {
    this.dialogData = group;
    const url = `${this.urlEndpoint}/updateGroup`;

    return this.http.put(url, group, httpOptions).pipe(
      catchError(this.handleError<any>('updateGroup'))
    );
  }

  getUsersByGroup(group: AuthGroup): Observable<AuthUser> {
    const url = `${this.urlEndpoint}/getUsers/${group.idGroup}`;
    return this.http.get<AuthUser>(url).pipe(
      catchError(this.handleError<AuthUser>('getUsersByGroup id=${id}')));
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
