package API.OPTATIVA.demo.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.stereotype.Service;
import API.OPTATIVA.demo.Persistence.Model.Status;
import API.OPTATIVA.demo.Persistence.Repository.StatusRepository;
import API.OPTATIVA.demo.Service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired // Inyección del repositorio
    private StatusRepository statusRepository; // Repositorio de Status

    @Override // Obtener todos los status
    public List<Status> getAllStatus() { // Obtener todos los status
        return statusRepository.findAll(); // Llama al método findAll del repositorio
    }

    @Override // Obtener status por ID
    public Status getStatusById(Integer id) { // Obtener status por ID
        return statusRepository.findById(id).orElse(null); // devuelve null si no existe
    }

    @Override // Buscar status por nombre
    public List<Status> getStatusByName(String name) { // Buscar status por nombre
        return statusRepository.findByStatusNameContainingIgnoreCase(name); // Llama al método del repositorio
    }

    @Override // Crear un nuevo status
    public Status createStatus(Status status) { // Crear un nuevo status
        return statusRepository.save(status); // Guarda el status en la base de datos
    }

    @Override // Actualizar un status existente
    public Status updateStatus(Integer id, Status status) { // Actualizar un status existente
        Status existing = statusRepository.findById(id).orElse(null); // devuelve null si no existe
        if (existing != null) { // Si el status existe, actualizamos sus campos
            existing.setStatusName(status.getStatusName()); // Actualiza el nombre del status
            return statusRepository.save(existing); // Guarda los cambios
        }
        return null; // si no existe, devolvemos null
    }

    @Override // Eliminar un status por ID
    public void deleteStatus(Integer id) { // Eliminar un status por ID
        statusRepository.deleteById(id); // Llama al método deleteById del repositorio

    }
}