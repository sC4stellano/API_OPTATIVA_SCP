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
    private final ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Integer id) {
        return projectRepository.findById(id).orElse(null); // devuelve null si no existe
    }

    @Override
    public List<Project> searchProjectsByName(String word) {
        return projectRepository.findByNameContainingIgnoreCase(word);
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Integer id, Project project) {
        Project existing = projectRepository.findById(id).orElse(null); // devuelve null si no existe
        if (existing != null) {
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

    @Override
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }

    @Override
    public List<Project> getProjectsByStatus(Status status) {
        return projectRepository.findByStatus(status);
    }
}
