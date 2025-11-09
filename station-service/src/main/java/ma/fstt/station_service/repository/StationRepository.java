package ma.fstt.station_service.repository;

import ma.fstt.station_service.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {}
