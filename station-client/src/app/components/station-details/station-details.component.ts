import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HistoCarburantService, HistoCarb } from '../../services/histo-carburant.service';

interface CarburantInfo {
  id: number;
  date: string;
  prix: number;
  carburantNom: string;
  carburantDescription: string;
}

interface Carburant {
  id: number;
  nom: string;
  description: string;
}

@Component({
  selector: 'app-station-details',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './station-details.component.html',
  styleUrls: ['./station-details.component.css']
})
export class StationDetailsComponent implements OnInit {

  stationId!: number;
  station: any;
  histoCarburants: CarburantInfo[] = [];
  carburantsDisponibles: Carburant[] = [];

  selectedCarburantId: number = 0;
  nouveauPrix: number = 0;

  nouveauCarburantId: number = 0;
  nouveauCarburantPrix: number = 0;

  private stationApi = 'http://localhost:8080/api/stations';
  private histoApi = 'http://localhost:8080/api/histo';
  private carburantApi = 'http://localhost:8080/api/carburants';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private histoService: HistoCarburantService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.stationId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadStationDetails();
    this.loadCarburants();
    this.loadCarburantsDisponibles();
  }

  // Charger la station
  loadStationDetails() {
    this.http.get(`${this.stationApi}/${this.stationId}`).subscribe({
      next: (data) => this.station = data,
      error: () => alert("Erreur lors du chargement de la station")
    });
  }

  // Charger les carburants associés à la station
  loadCarburants() {
    this.http.get<CarburantInfo[]>(`${this.histoApi}/station/${this.stationId}`).subscribe({
      next: (data) => this.histoCarburants = data,
      error: () => alert("Erreur lors du chargement des carburants")
    });
  }

  // Charger les carburants existants (pour ajouter)
  loadCarburantsDisponibles() {
    this.http.get<Carburant[]>(this.carburantApi).subscribe({
      next: (data) => this.carburantsDisponibles = data,
      error: () => alert("Erreur lors du chargement des carburants disponibles")
    });
  }

  // Ajouter un carburant avec prix
  ajouterCarburant() {
    if (!this.nouveauCarburantId || !this.nouveauCarburantPrix) {
      alert("Veuillez choisir un carburant et indiquer un prix.");
      return;
    }

    const payload = {
      stationId: this.stationId,
      carburantId: this.nouveauCarburantId,
      prix: this.nouveauCarburantPrix,
      date: new Date().toISOString().split('T')[0]
    };

    this.http.post(`${this.histoApi}`, payload).subscribe({
      next: () => {
        alert("Carburant ajouté avec succès !");
        this.loadCarburants();
        this.nouveauCarburantId = 0;
        this.nouveauCarburantPrix = 0;
      },
      error: () => alert("Erreur lors de l’ajout du carburant.")
    });
  }

  // Modifier le prix d’un carburant existant
  modifierPrix(item: CarburantInfo) {
    const nouveauPrix = prompt(`Nouveau prix pour ${item.carburantNom} :`, item.prix.toString());
    if (nouveauPrix === null) return;

    const updated = { ...item, prix: Number(nouveauPrix) };

    this.http.put(`${this.histoApi}/${item.id}`, updated).subscribe({
      next: () => {
        alert("Prix modifié avec succès !");
        this.loadCarburants();
      },
      error: () => alert("Erreur lors de la modification du prix.")
    });
  }

  // Supprimer un carburant d’une station
  supprimerCarburant(id: number) {
    if (!confirm("Voulez-vous vraiment supprimer ce carburant ?")) return;

    this.http.delete(`${this.histoApi}/${id}`).subscribe({
      next: () => {
        alert("Carburant supprimé avec succès !");
        this.loadCarburants();
      },
      error: () => alert("Erreur lors de la suppression du carburant.")
    });
  }

  // Retour
  backToList() {
    this.router.navigate(['/stations']);
  }
}
