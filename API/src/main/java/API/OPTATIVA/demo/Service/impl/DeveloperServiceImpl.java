package API.OPTATIVA.demo.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import API.OPTATIVA.demo.Persistence.Model.Developer;
import API.OPTATIVA.demo.Persistence.Repository.DeveloperRepository;
import API.OPTATIVA.demo.Service.DeveloperService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository developerRepository; // Inyección del repositorio

    @Override // Obtener todos los developers
    public List<Developer> getAllDevelopers() { // Obtener todos los developers
        return developerRepository.findAll(); // Llama al método findAll del repositorio
    }

    @Override // Obtener developer por ID
    public Developer getDeveloperById(Integer id) { // Obtener developer por ID
        return developerRepository.findById(id).orElse(null); // devuelve null si no existe
    }

    @Override // Obtener developers por nombre
    public List<Developer> getDevelopersByName(String name) { // Obtener developers por nombre
        return developerRepository.findByNameContainingIgnoreCase(name); // Llama al método del repositorio
    }

    @Override // Crear un nuevo developer
    public Developer createDeveloper(Developer developer) { // Crear un nuevo developer
        return developerRepository.save(developer); // Guarda el developer en la base de datos
    }

    @Override // Actualizar un developer existente
    public Developer updateDeveloper(Integer id, Developer developer) { // Actualizar un developer existente
        Developer existing = developerRepository.findById(id).orElse(null); // devuelve null si no existe
        if (existing != null) { // Si el developer existe, actualizamos sus campos
            existing.setName(developer.getName());
            existing.setSurname(developer.getSurname());
            existing.setEmail(developer.getEmail());
            existing.setLinUrl(developer.getLinUrl());
            existing.setGitUrl(developer.getGitUrl());
            return developerRepository.save(existing);
        }
        return null; // si no existe, devolvemos null
    }

    @Override // Eliminar un developer por ID
    public void deleteDeveloper(Integer id) { // Eliminar un developer por ID
        developerRepository.deleteById(id); // Llama al método deleteById del repositorio
    }
}
