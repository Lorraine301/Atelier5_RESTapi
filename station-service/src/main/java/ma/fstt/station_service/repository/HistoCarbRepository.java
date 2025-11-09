package ma.fstt.station_service.repository;

import ma.fstt.station_service.model.HistoCarb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoCarbRepository extends JpaRepository<HistoCarb, Long> {
    List<HistoCarb> findByStationId(Long stationId);
    List<HistoCarb> findByCarburantId(Long carburantId);
}
