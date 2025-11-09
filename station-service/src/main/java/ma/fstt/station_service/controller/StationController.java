package ma.fstt.station_service.controller;


import ma.fstt.station_service.model.Station;
import ma.fstt.station_service.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping
    public List<Station> all() { return stationService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Station> byId(@PathVariable Long id) {
        return stationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Station> create(@RequestBody Station s) {
        Station saved = stationService.save(s);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Station> update(@PathVariable Long id, @RequestBody Station s) {
        if(!stationService.findById(id).isPresent()) return ResponseEntity.notFound().build();
        s.setId(id);
        return ResponseEntity.ok(stationService.save(s));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
