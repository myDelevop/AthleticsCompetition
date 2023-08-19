import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/auth/authentication.service';
import { first } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private toastr: ToastrService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.authenticationService.logout();
    this.returnUrl = this.route.snapshot.queryParams[`returnUrl`] || '/hello';
  }

  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {

    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService.login(this.f.username.value, this.f.password.value)
      .pipe(first()).subscribe(
        data => {
/*          alert(data.text());
          if(data.status === 200) {
            if(data.text() === 'NOEXISTS') {
              //this.toastr.warn('Utente inesistente');
            } else {
//              this.toastr.message('YAAAAAAAAAAAAAAA');
              alert('YAAAA');
            }
          }*/
          //alert('qui' + data.text());
          // this.router.navigate([this.returnUrl]);
        },
      error => {

        if(error.status === 401) {
          this.toastr.error('Utente esistente ma non autorizzato');
        } else if (error.status === 500) {
          this.toastr.error('Si è verificato un errore inaspettato sul server');
        } else {
          this.toastr.error('Errore inaspettato');
        }

        console.error('Error occurred. Details: ' + error.status + error.name + ' ' + error.message);
        this.error = error;
        this.loading = false;
      });
    }

}
