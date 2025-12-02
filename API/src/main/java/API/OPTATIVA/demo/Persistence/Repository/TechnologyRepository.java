package API.OPTATIVA.demo.Persistence.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import API.OPTATIVA.demo.Persistence.Model.Technology;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Integer> {
    // tecnologias por nombre
    List<Technology> findByTechNameContainingIgnoreCase(String name);

}
