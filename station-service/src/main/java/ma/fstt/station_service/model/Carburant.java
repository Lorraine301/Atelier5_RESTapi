package ma.fstt.station_service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
        import java.util.List;
        import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carburant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;

    @OneToMany(mappedBy = "carburant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("carb-histo")
    private List<HistoCarb> histoCarbs;

}
