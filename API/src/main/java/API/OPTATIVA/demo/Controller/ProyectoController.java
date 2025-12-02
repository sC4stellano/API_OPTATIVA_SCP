package API.OPTATIVA.demo.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import API.OPTATIVA.demo.Persistence.Model.Project;
import API.OPTATIVA.demo.Service.ProjectService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/projects")
@AllArgsConstructor
public class ProyectoController {
    private final ProjectService projectService;

    // 1. GET /projects -> todos los proyectos
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return projects.isEmpty()
                ? ResponseEntity.noContent().build() // 204 si no hay proyectos
                : ResponseEntity.ok(projects); // 200 OK si hay proyectos
    }

    // 2. GET /projects/id/{id} -> proyecto por id
    @GetMapping("/id/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Integer id) {
        Project project = projectService.getProjectById(id);
        return (project != null) ? ResponseEntity.ok(project) : ResponseEntity.notFound().build();
    }

    // 3. GET /projects/search/{word} -> proyectos que contienen la palabra en el
    // nombre
    @GetMapping("/search/{word}")
    public ResponseEntity<List<Project>> getProjectsByWord(@PathVariable String word) {
        List<Project> projects = projectService.searchProjectsByName(word);
        return (projects.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(projects);
    }

    // 4. POST /projects -> insertar proyecto
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project created = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(created); // 201 Created
    }

    // 5. PUT /projects/{id} -> editar proyecto
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Integer id, @RequestBody Project project) {
        Project updated = projectService.updateProject(id, project);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // 6. DELETE /projects/{id} -> eliminar proyecto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        Project project = projectService.getProjectById(id);
        if (project == null) {
            return ResponseEntity.notFound().build(); // 404 si no existe
        }
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build(); // 204 si se eliminó
    }
}
