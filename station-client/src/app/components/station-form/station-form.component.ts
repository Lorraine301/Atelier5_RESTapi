import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Station } from '../../models/station.model';

@Component({
  selector: 'app-station-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './station-form.component.html',
  styleUrls: ['./station-form.component.css']
})
export class StationFormComponent implements OnInit {

  station: Station = { nom: '', adresse: '', ville: '' };
  isEditMode = false;
  private apiUrl = 'http://localhost:8080/api/stations';

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.loadStation(+id);
    }
  }

  //  Charger une station existante pour la modification
  loadStation(id: number) {
    this.http.get<Station>(`${this.apiUrl}/${id}`).subscribe({
      next: (data) => this.station = data,
      error: (err) => alert('Erreur lors du chargement : ' + err.message)
    });
  }

  //  Sauvegarder ou mettre à jour une station
  saveStation() {
    if (this.isEditMode && this.station.id) {
      // mode édition
      this.http.put(`${this.apiUrl}/${this.station.id}`, this.station).subscribe({
        next: () => {
          alert('Station mise à jour avec succès');
          this.router.navigate(['/stations']);
        },
        error: () => alert('Erreur lors de la mise à jour ')
      });
    } else {
      // mode ajout
      this.http.post(this.apiUrl, this.station).subscribe({
        next: () => {
          alert('Station ajoutée avec succès');
          this.router.navigate(['/stations']);
        },
        error: () => alert('Erreur lors de l’ajout ')
      });
    }
  }

  //  Annuler et revenir à la liste
  cancel() {
    this.router.navigate(['/stations']);
  }
}
