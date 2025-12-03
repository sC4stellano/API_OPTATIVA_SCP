package API.OPTATIVA.demo.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import API.OPTATIVA.demo.Persistence.Model.Project;
import API.OPTATIVA.demo.Persistence.Model.Status;
import API.OPTATIVA.demo.Persistence.Repository.ProjectRepository;
import API.OPTATIVA.demo.Service.ProjectService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository; // Inyección del repositorio

    @Override // Obtener todos los proyectos
    public List<Project> getAllProjects() { // Obtener todos los proyectos
        return projectRepository.findAll(); // Llama al método findAll del repositorio
    }

    @Override // Obtener proyecto por ID
    public Project getProjectById(Integer id) { // Obtener proyecto por ID
        return projectRepository.findById(id).orElse(null); // devuelve null si no existe
    }

    @Override // Buscar proyectos por nombre
    public List<Project> searchProjectsByName(String word) { // Buscar proyectos por nombre
        return projectRepository.findByNameContainingIgnoreCase(word); // Llama al método del repositorio
    }

    @Override // Crear un nuevo proyecto
    public Project createProject(Project project) { // Crear un nuevo proyecto
        return projectRepository.save(project); // Guarda el proyecto en la base de datos
    }

    @Override // Actualizar un proyecto existente
    public Project updateProject(Integer id, Project project) { // Actualizar un proyecto existente
        Project existing = projectRepository.findById(id).orElse(null); // devuelve null si no existe
        if (existing != null) { // Si el proyecto existe, actualizamos sus campos
            existing.setName(project.getName());
            existing.setDescription(project.getDescription());
            existing.setStarDate(project.getStarDate());
            existing.setEndDate(project.getEndDate());
            existing.setDemoUrl(project.getDemoUrl());
            existing.setPicture(project.getPicture());
            existing.setStatus(project.getStatus());
            existing.setDevelopersWorking(project.getDevelopersWorking());
            existing.setTechnologiesUsed(project.getTechnologiesUsed());

            return projectRepository.save(existing);
        }
        return null; // si no existe, devolvemos null
    }

    @Override // Eliminar un proyecto por ID
    public void deleteProject(Integer id) { // Eliminar un proyecto por ID
        projectRepository.deleteById(id); // Llama al método deleteById del repositorio
    }

    @Override // Obtener proyectos por estado
    public List<Project> getProjectsByStatus(Status status) { // Obtener proyectos por estado
        return projectRepository.findByStatus(status); // Llama al método findByStatus del repositorio
    }
}
