package API.OPTATIVA.demo.Persistence.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import API.OPTATIVA.demo.Persistence.Model.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
    // Repositorio para la entidad Developer
    // Método para buscar developers por nombre
    List<Developer> findByNameContainingIgnoreCase(String name);
}
