package API.OPTATIVA.demo.Persistence.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import API.OPTATIVA.demo.Persistence.Model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    // Buscar status por nombre
    List<Status> findByStatusNameContainingIgnoreCase(String name);

}
