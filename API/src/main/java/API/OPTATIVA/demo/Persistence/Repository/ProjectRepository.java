package API.OPTATIVA.demo.Persistence.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import API.OPTATIVA.demo.Persistence.Model.Project;
import API.OPTATIVA.demo.Persistence.Model.Status;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    // Búsqueda de proyectos por palabra en el nombre (query simple)
    List<Project> findByNameContainingIgnoreCase(String word);

    // Búsqueda de proyectos por estado
    List<Project> findByStatus(Status status);

    // Búsqueda de proyectos entre fechas
    List<Project> findByStarDateBetween(LocalDate start, LocalDate end);

}
