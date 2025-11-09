package ma.fstt.station_service.controller;



import ma.fstt.station_service.model.Carburant;
import ma.fstt.station_service.service.CarburantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/carburants")
@CrossOrigin("*")
public class CarburantController {

    @Autowired
    private CarburantService carburantService;

    //  GET - Tous les carburants
    @GetMapping
    public List<Carburant> getAllCarburants() {
        return carburantService.findAll();
    }

    // GET - Carburant par ID
    @GetMapping("/{id}")
    public ResponseEntity<Carburant> getCarburantById(@PathVariable Long id) {
        return carburantService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  POST - Ajouter un carburant
    @PostMapping
    public ResponseEntity<Carburant> createCarburant(@RequestBody Carburant carburant) {
        Carburant saved = carburantService.save(carburant);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //  PUT - Modifier un carburant
    @PutMapping("/{id}")
    public ResponseEntity<Carburant> updateCarburant(@PathVariable Long id, @RequestBody Carburant carburant) {
        if (!carburantService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        carburant.setId(id);
        Carburant updated = carburantService.save(carburant);
        return ResponseEntity.ok(updated);
    }

    // DELETE - Supprimer un carburant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarburant(@PathVariable Long id) {
        if (!carburantService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        carburantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
