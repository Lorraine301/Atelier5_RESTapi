import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface HistoCarb {
  id?: number;
  date: string;
  prix: number;
  carburantNom?: string;
  carburantDescription?: string;
}

@Injectable({ providedIn: 'root' })
export class HistoCarburantService {
  private baseUrl = 'http://localhost:8080/api/histo';

  constructor(private http: HttpClient) {}

  getByStation(stationId: number): Observable<HistoCarb[]> {
    return this.http.get<HistoCarb[]>(`${this.baseUrl}/station/${stationId}`);
  }

  addCarburantToStation(stationId: number, carburantId: number, prix: number, date: string): Observable<HistoCarb> {
    return this.http.post<HistoCarb>(
      `${this.baseUrl}/station/${stationId}/carburant/${carburantId}?prix=${prix}&date=${date}`,
      {}
    );
  }

  updatePrix(histoId: number, prix: number): Observable<HistoCarb> {
    return this.http.put<HistoCarb>(`${this.baseUrl}/${histoId}/prix?prix=${prix}`, {});
  }

  deleteHisto(histoId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${histoId}`);
  }
}
