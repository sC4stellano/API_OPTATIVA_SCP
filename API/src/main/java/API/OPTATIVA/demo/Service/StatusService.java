package API.OPTATIVA.demo.Service;

import java.util.List;
import API.OPTATIVA.demo.Persistence.Model.Status;

public interface StatusService { // Servicio para la entidad Status
    List<Status> getAllStatus(); // Obtener todos los status

    Status getStatusById(Integer id); // Obtener status por ID

    List<Status> getStatusByName(String name); // Obtener status por nombre

    Status createStatus(Status status); // Crear un nuevo status

    Status updateStatus(Integer id, Status status); // Actualizar un status existente

    void deleteStatus(Integer id); // Eliminar un status por ID
}