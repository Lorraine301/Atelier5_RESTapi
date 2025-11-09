package ma.fstt.station_service.repository;

import ma.fstt.station_service.model.Carburant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarburantRepository extends JpaRepository<Carburant, Long> {}

