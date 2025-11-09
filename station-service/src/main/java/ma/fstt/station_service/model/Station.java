package ma.fstt.station_service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
        import java.util.List;
        import lombok.*;

@Entity
@Data               // Génère getters, setters, toString, equals, hashCode
@NoArgsConstructor  // Génère un constructeur sans arguments
@AllArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String adresse;
    private String ville;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("station-histo")
    private List<HistoCarb> histoCarbs;


    // constructeurs, getters/setters
}
