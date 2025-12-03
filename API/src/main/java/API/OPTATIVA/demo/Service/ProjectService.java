package API.OPTATIVA.demo.Service;

import java.util.List;

import API.OPTATIVA.demo.Persistence.Model.Project;
import API.OPTATIVA.demo.Persistence.Model.Status;

public interface ProjectService { // Servicio para la entidad Project

    // Obtener todos los proyectos
    public List<Project> getAllProjects();

    // Ontener poryecto por Id
    public Project getProjectById(Integer id);

    // Buscar proyectos por palabra en el nombre
    public List<Project> searchProjectsByName(String word);

    // Crear un proyecto
    public Project createProject(Project project);

    // Actualizar proyecto
    public Project updateProject(Integer id, Project project);

    // Eliminar proyecto
    public void deleteProject(Integer id);

    // Buscar proyectos por status (opcional)
    public List<Project> getProjectsByStatus(Status status);
}
