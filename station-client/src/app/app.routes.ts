import { Routes } from '@angular/router';
import { StationListComponent } from './components/station-list/station-list.component';
import { StationFormComponent } from './components/station-form/station-form.component';
import { StationDetailsComponent } from './components/station-details/station-details.component';

export const routes: Routes = [
  { path: '', redirectTo: 'stations', pathMatch: 'full' },
  { path: 'stations', component: StationListComponent },
  { path: 'stations/add', component: StationFormComponent },
  { path: 'stations/edit/:id', component: StationFormComponent },
  { path: 'stations/details/:id', component: StationDetailsComponent },
];
