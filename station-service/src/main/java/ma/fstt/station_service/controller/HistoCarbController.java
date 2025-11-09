package ma.fstt.station_service.controller;

import ma.fstt.station_service.dto.HistoCarbDTO;
import ma.fstt.station_service.dto.HistoCarbResponseDTO;
import ma.fstt.station_service.model.HistoCarb;
import ma.fstt.station_service.service.HistoCarbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/histo")
@CrossOrigin("*")
public class HistoCarbController {

    @Autowired
    private HistoCarbService histoCarbService;


    // GET - Tous les historiques
    @GetMapping
    public List<HistoCarb> getAllHisto() {
        return histoCarbService.findAll();
    }

    // GET - Historique par ID
    @GetMapping("/{id}")
    public ResponseEntity<HistoCarb> getById(@PathVariable Long id) {
        return histoCarbService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  GET - Historique par Station
    @GetMapping("/station/{stationId}")
    public List<HistoCarbResponseDTO> getByStation(@PathVariable Long stationId) {
        List<HistoCarb> histos = histoCarbService.findByStation(stationId);

        return histos.stream()
                .map(h -> new HistoCarbResponseDTO(
                        h.getId(),
                        h.getDate(),
                        h.getPrix(),
                        h.getCarburant() != null ? h.getCarburant().getNom() : "—",
                        h.getCarburant() != null ? h.getCarburant().getDescription() : "—"
                ))
                .toList();
    }


    //  GET - Historique par Carburant
    @GetMapping("/carburant/{carburantId}")
    public List<HistoCarb> getByCarburant(@PathVariable Long carburantId) {
        return histoCarbService.findByCarburant(carburantId);
    }

    //  POST - Ajouter un nouveau prix (avec vérification des entités)
    @PostMapping
    public ResponseEntity<?> addPrix(@RequestBody HistoCarbDTO dto) {
        Optional<HistoCarb> created = histoCarbService.addPrix(
                dto.stationId,
                dto.carburantId,
                dto.date,
                dto.prix
        );

        if (created.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Station ou Carburant introuvable !");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(created.get());
    }

    // PUT - Modifier un historique existant
    @PutMapping("/{id}")
    public ResponseEntity<HistoCarb> updateHisto(@PathVariable Long id, @RequestBody HistoCarb updatedHisto) {
        Optional<HistoCarb> existing = histoCarbService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        updatedHisto.setId(id);
        HistoCarb saved = histoCarbService.save(updatedHisto);
        return ResponseEntity.ok(saved);
    }

    //  DELETE - Supprimer un historique
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHisto(@PathVariable Long id) {
        Optional<HistoCarb> existing = histoCarbService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        histoCarbService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
