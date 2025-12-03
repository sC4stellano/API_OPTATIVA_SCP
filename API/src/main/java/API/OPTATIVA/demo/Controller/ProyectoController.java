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
        List<Project> projects = projectService.getAllProjects();// Llama al servicio para recuperar la lista
                                                                 // de proyectos desde la BD
        return projects.isEmpty()
                ? ResponseEntity.noContent().build() // 204 si no hay proyectos
                : ResponseEntity.ok(projects); // 200 OK si hay proyectos
    }

    // 2. GET /projects/id/{id} -> proyecto por id
    @GetMapping("/id/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Integer id) {
        Project project = projectService.getProjectById(id); // Busca el proyecto por id en la capa de servicio.
        return (project != null) ? ResponseEntity.ok(project) : ResponseEntity.notFound().build();
    } // 200 OK si existe // 404 si no existe

    // 3. GET /projects/search/{word} -> proyectos que contienen la palabra en el
    // nombre
    @GetMapping("/search/{word}") // Devuelve los proyectos cuyo nombre contenga 'word'
    public ResponseEntity<List<Project>> getProjectsByWord(@PathVariable String word) {
        List<Project> projects = projectService.searchProjectsByName(word);// obtiene la lista
        return (projects.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(projects);
    } // 200 OK si existe // 404 si no existe

    // 4. POST /projects -> insertar proyecto
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) { // Crea un nuevo proyecto a partir del
                                                                                 // JSON recibido en el cuerpo.
        Project created = projectService.createProject(project); // Pasa el objeto recibido al servicio para guardarlo
                                                                 // en la BD.
        return ResponseEntity.status(HttpStatus.CREATED).body(created); // 201 Created
    }

    // 5. PUT /projects/{id} -> editar proyecto
    @PutMapping("/{id}") // Actualiza un proyecto existente.
    public ResponseEntity<Project> updateProject(@PathVariable Integer id, @RequestBody Project project) {
        // Recibe id y el proyecto actualizado en el cuerpo.

        Project updated = projectService.updateProject(id, project);// Llama al servicio para actualizar.
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    } // 200 OK si existe // 404 si no existe

    // 6. DELETE /projects/{id} -> eliminar proyecto
    @DeleteMapping("/{id}") // Elimina un proyecto por su id.
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) { // Verifica si existe.
        Project project = projectService.getProjectById(id);
        if (project == null) {
            return ResponseEntity.notFound().build(); // 404 si no existe
        }
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build(); // 204 si se eliminó
    }
}
