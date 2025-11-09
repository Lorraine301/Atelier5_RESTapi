
package ma.fstt.station_service.dto;

import java.time.LocalDate;

public class HistoCarbResponseDTO {
    public Long id;
    public LocalDate date;
    public double prix;
    public String carburantNom;
    public String carburantDescription;

    public HistoCarbResponseDTO(Long id, LocalDate date, double prix, String carburantNom, String carburantDescription) {
        this.id = id;
        this.date = date;
        this.prix = prix;
        this.carburantNom = carburantNom;
        this.carburantDescription = carburantDescription;
    }
}
