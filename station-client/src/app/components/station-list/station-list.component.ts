import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { Station } from '../../models/station.model';

@Component({
  selector: 'app-station-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './station-list.component.html',
  styleUrls: ['./station-list.component.css']
})
export class StationListComponent implements OnInit {
  stations: Station[] = [];

  private apiUrl = 'http://localhost:8080/api/stations';

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.getStations().subscribe(data => this.stations = data);
  }

  //  Récupération de toutes les stations
  getStations(): Observable<Station[]> {
    return this.http.get<Station[]>(this.apiUrl);
  }

  //  Redirection vers la page de modification
  editStation(station: Station) {
    this.router.navigate(['/stations/edit', station.id]);
  }

  //  Suppression d’une station
  deleteStation(id: number | undefined) {
    if (!id) return;
    if (confirm('Voulez-vous vraiment supprimer cette station ?')) {
      this.http.delete(`${this.apiUrl}/${id}`).subscribe({
        next: () => {
          alert('Station supprimée avec succès');
          this.stations = this.stations.filter(s => s.id !== id);
        },
        error: err => {
          console.error(err);
          alert('Erreur lors de la suppression');
        }
      });
    }
  }

  //  Affichage des détails d’une station
  viewDetails(station: Station) {
    this.router.navigate(['/stations/details', station.id]);
  }

  //  Redirection vers la page d’ajout
  addStation() {
    this.router.navigate(['/stations/add']);
  }
}

