package ma.fstt.station_service.service;



import ma.fstt.station_service.model.Carburant;
import ma.fstt.station_service.repository.CarburantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarburantService {

    private final CarburantRepository carburantRepository;

    @Autowired
    public CarburantService(CarburantRepository carburantRepository) {
        this.carburantRepository = carburantRepository;
    }

    // Liste de tous les carburants
    public List<Carburant> findAll() {
        return carburantRepository.findAll();
    }

    // Trouver un carburant par ID
    public Optional<Carburant> findById(Long id) {
        return carburantRepository.findById(id);
    }

    // Ajouter ou mettre à jour un carburant
    public Carburant save(Carburant carburant) {
        return carburantRepository.save(carburant);
    }

    // Supprimer un carburant par ID
    public void delete(Long id) {
        carburantRepository.deleteById(id);
    }

    // Vérifier l’existence d’un carburant
    public boolean existsById(Long id) {
        return carburantRepository.existsById(id);
    }
}
