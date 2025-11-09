package ma.fstt.station_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
        import java.time.LocalDate;
        import lombok.*;

@Entity
@Data               // Génère getters, setters, toString, equals, hashCode
@NoArgsConstructor  // Génère un constructeur sans arguments
@AllArgsConstructor
public class HistoCarb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private double prix;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    @JsonBackReference("station-histo")
    private Station station;

    @ManyToOne
    @JoinColumn(name = "carburant_id", nullable = false)
    @JsonBackReference("carb-histo")
    private Carburant carburant;


    // constructeurs, getters/setters
}
