package API.OPTATIVA.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import API.OPTATIVA.demo.Persistence.Model.Developer;
import API.OPTATIVA.demo.Service.DeveloperService;
import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("api/v1/developers")
@AllArgsConstructor
public class DeveloperController {
    private final DeveloperService developerService;

    // GET /developers -> todos los developers
    @GetMapping
    public ResponseEntity<List<Developer>> getAllDevelopers() {
        List<Developer> developers = developerService.getAllDevelopers(); // Llama al servicio para recuperar la lista
                                                                          // de developers desde la BD
        return developers.isEmpty()
                ? ResponseEntity.noContent().build() // 204 si no hay developers
                : ResponseEntity.ok(developers); // 200 OK con datos
    }

    // GET /developers/{id} -> obtener developer por id
    @GetMapping("/{id}")
    public ResponseEntity<Developer> getDeveloperById(@PathVariable Integer id) {
        Developer developer = developerService.getDeveloperById(id);// Busca el developer por id en la capa de servicio.
        return (developer != null)
                ? ResponseEntity.ok(developer) // 200 OK si existe
                : ResponseEntity.notFound().build(); // 404 si no existe
    }

    // GET /developers/search/{name} -> developers que contienen 'name'
    @GetMapping("/search/{name}") // Devuelve los developers cuyo nombre contenga 'name' (lógica en el
                                  // servicio/repositorio).
    public ResponseEntity<List<Developer>> getDevelopersByName(@PathVariable String name) {
        List<Developer> developers = developerService.getDevelopersByName(name); // obtiene la lista
        return developers.isEmpty()
                ? ResponseEntity.noContent().build() // 204 si no hay resultados
                : ResponseEntity.ok(developers); // 200 OK con datos
    }

    // POST /developers -> crear nuevo developer
    @PostMapping // Crea un nuevo developer a partir del JSON recibido en el cuerpo.
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer) {
        Developer created = developerService.createDeveloper(developer);// Pasa el objeto recibido al servicio para
                                                                        // guardarlo en la BD.
        return ResponseEntity.status(HttpStatus.CREATED).body(created); // 201 Created
    }

    // PUT /developers/{id} -> actualizar developer existente
    @PutMapping("/{id}") // Actualiza un developer existente.
    public ResponseEntity<Developer> updateDeveloper(@RequestBody Developer developer, @PathVariable Integer id) {
        Developer updated = developerService.updateDeveloper(id, developer); // Llama al servicio para actualizar.
        return (updated != null)
                ? ResponseEntity.ok(updated) // 200 OK
                : ResponseEntity.notFound().build(); // 404 si no existe
    }

    // DELETE /developers/{id} -> eliminar developer
    @DeleteMapping("/{id}") // Elimina un developer por su id.
    public ResponseEntity<Void> deleteDeveloper(@PathVariable Integer id) {
        Developer developer = developerService.getDeveloperById(id); // Verifica si existe.
        if (developer == null) {
            return ResponseEntity.notFound().build(); // 404 si no existe
        }
        developerService.deleteDeveloper(id);
        return ResponseEntity.noContent().build(); // 204 si se eliminó
    }
}
