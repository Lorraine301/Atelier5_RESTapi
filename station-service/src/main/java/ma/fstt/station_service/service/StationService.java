package ma.fstt.station_service.service;


import ma.fstt.station_service.model.Station;
import ma.fstt.station_service.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationService {
    @Autowired
    private StationRepository stationRepo;

    public List<Station> findAll() { return stationRepo.findAll(); }
    public Optional<Station> findById(Long id) { return stationRepo.findById(id); }
    public Station save(Station s) { return stationRepo.save(s); }
    public void delete(Long id) { stationRepo.deleteById(id); }
}
