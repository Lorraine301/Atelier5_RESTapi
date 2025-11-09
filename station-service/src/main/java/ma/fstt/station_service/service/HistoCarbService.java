package ma.fstt.station_service.service;

import ma.fstt.station_service.model.Carburant;
import ma.fstt.station_service.model.HistoCarb;
import ma.fstt.station_service.model.Station;
import ma.fstt.station_service.repository.CarburantRepository;
import ma.fstt.station_service.repository.HistoCarbRepository;
import ma.fstt.station_service.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HistoCarbService {

    private final HistoCarbRepository histoCarbRepository;
    private final StationRepository stationRepository;
    private final CarburantRepository carburantRepository;

    @Autowired
    public HistoCarbService(HistoCarbRepository histoCarbRepository,
                            StationRepository stationRepository,
                            CarburantRepository carburantRepository) {
        this.histoCarbRepository = histoCarbRepository;
        this.stationRepository = stationRepository;
        this.carburantRepository = carburantRepository;
    }

    // ========== Méthodes CRUD de base ==========

    public List<HistoCarb> findAll() {
        return histoCarbRepository.findAll();
    }

    public Optional<HistoCarb> findById(Long id) {
        return histoCarbRepository.findById(id);
    }

    public HistoCarb save(HistoCarb histoCarb) {
        return histoCarbRepository.save(histoCarb);
    }

    public void delete(Long id) {
        histoCarbRepository.deleteById(id);
    }

    // Récupérer les prix d'une station donnée
    public List<HistoCarb> findByStation(Long stationId) {
        return histoCarbRepository.findByStationId(stationId);
    }

    // Récupérer les prix d'un carburant donné
    public List<HistoCarb> findByCarburant(Long carburantId) {
        return histoCarbRepository.findByCarburantId(carburantId);
    }

    // ========== Méthode spécifique demandée ==========

    /**
     * Ajoute un nouveau prix pour une station et un carburant donnés.
     * Vérifie l'existence des entités avant enregistrement.
     */
    public Optional<HistoCarb> addPrix(Long stationId, Long carburantId, LocalDate date, double prix) {
        Optional<Station> stationOpt = stationRepository.findById(stationId);
        Optional<Carburant> carburantOpt = carburantRepository.findById(carburantId);

        if (stationOpt.isEmpty() || carburantOpt.isEmpty()) {
            return Optional.empty(); // soit station soit carburant n’existe pas
        }

        HistoCarb histo = new HistoCarb();
        histo.setDate(date);
        histo.setPrix(prix);
        histo.setStation(stationOpt.get());
        histo.setCarburant(carburantOpt.get());

        HistoCarb saved = histoCarbRepository.save(histo);
        return Optional.of(saved);
    }
}
