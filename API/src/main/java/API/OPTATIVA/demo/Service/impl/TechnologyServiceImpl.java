package API.OPTATIVA.demo.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import API.OPTATIVA.demo.Persistence.Model.Technology;
import API.OPTATIVA.demo.Persistence.Repository.TechnologyRepository;
import API.OPTATIVA.demo.Service.TechnologyService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TechnologyServiceImpl implements TechnologyService {
    private final TechnologyRepository technologyRepository; // Repositorio de Technology

    @Override // Obtener todas las tecnologías
    public List<Technology> getAllTechnologies() { // Obtener todas las tecnologías
        return technologyRepository.findAll(); // Llama al método findAll del repositorio
    }

    @Override // Obtener tecnología por ID
    public Technology getTechnologyById(Integer id) { // Obtener tecnología por ID
        return technologyRepository.findById(id).orElse(null); // devuelve null si no existe
    }

    @Override // Obtener tecnologías por nombre
    public List<Technology> getTechnologiesByName(String name) { // Obtener tecnologías por nombre
        return technologyRepository.findByTechNameContainingIgnoreCase(name); // Llama al método del repositorio
    }

    @Override // Crear una nueva tecnología
    public Technology createTechnology(Technology technology) { // Crear una nueva tecnología
        return technologyRepository.save(technology); // Guarda la tecnología en la base de datos
    }

    @Override // Actualizar una tecnología existente
    public Technology updateTechnology(Integer id, Technology technology) { // Actualizar una tecnología existente
        Technology existing = technologyRepository.findById(id).orElse(null); // devuelve null si no existe
        if (existing != null) { // Si la tecnología existe, actualizamos sus campos
            existing.setTechName(technology.getTechName()); // Actualiza el nombre de la tecnología
            existing.setProjects(technology.getProjects()); // Actualiza los proyectos asociados
            return technologyRepository.save(existing); // Guarda los cambios
        }
        return null; // si no existe, devolvemos null
    }

    @Override // Eliminar una tecnología por ID
    public void deleteTechnology(Integer id) { // Eliminar una tecnología por ID
        technologyRepository.deleteById(id); // Llama al método deleteById del repositorio
    }
}
