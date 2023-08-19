import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HelloComponent } from './components/hello/hello.component';


const routes: Routes = [
    { path: 'hello', component: HelloComponent }
];


/*
const routes: Routes = [
  { path: 'app-staz-forestali-controllori', component: StazForestaliControlloriComponent, canActivate: [AuthorizationGuard] },
  { path: 'app-utenti-gruppi', component: UtentiGruppiComponent, canActivate: [AuthorizationGuard] },
  { path: 'app-regole-checklist', component: GesioneRegoleChecklistComponent, canActivate: [AuthorizationGuard] },
  { path: 'app-gestione-template', component: GestioneTemplateComponent, canActivate: [AuthorizationGuard] },
  { path: 'assegnamento-controllori', component: AssegnamentoControlliComponent, canActivate: [AuthorizationGuard] },
  { path: 'app-anagrafiche', component: GestioneAnagraficheComponent, canActivate: [AuthorizationGuard] },
  { path: 'login', component: LoginComponent },
  // otherwise redirect to home
  { path: '**', redirectTo: '/', canActivate: [AuthorizationGuard] },

];
*/

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
