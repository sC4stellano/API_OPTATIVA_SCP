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

import API.OPTATIVA.demo.Persistence.Model.Technology;
import API.OPTATIVA.demo.Service.TechnologyService;
import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/technologies")
@AllArgsConstructor
public class TechnologyController {
    private final TechnologyService technologyService;

    // GET todas las tecnologías
    @GetMapping // Devuelve todas las tecnologías.
    public ResponseEntity<List<Technology>> getAllTechnologies() {
        List<Technology> techs = technologyService.getAllTechnologies(); // obtiene la lista
        return techs.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(techs);
    } // 200 OK con datos o 204 si no hay datos

    // GET por ID
    @GetMapping("/{id}") // Devuelve una tecnología por su id.
    public ResponseEntity<Technology> getTechnologyById(@PathVariable Integer id) {
        Technology tech = technologyService.getTechnologyById(id); // Busca la tecnología por id en la capa de servicio.
        return tech != null ? ResponseEntity.ok(tech) : ResponseEntity.notFound().build();
    } // 200 OK si existe o 404 si no existe

    // GET por nombre
    @GetMapping("/search/{name}") // Devuelve las tecnologías cuyo nombre contenga 'name'.
    public ResponseEntity<List<Technology>> getTechnologiesByName(@PathVariable String name) {
        List<Technology> techs = technologyService.getTechnologiesByName(name);
        // obtiene la lista
        return techs.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(techs);
        // 200 OK con datos o 204 si no hay datos
    }

    // POST nueva tecnología
    @PostMapping // Crea una nueva tecnología a partir del JSON recibido en el cuerpo.
    public ResponseEntity<Technology> createTechnology(@RequestBody Technology technology) {
        Technology created = technologyService.createTechnology(technology);
        // Pasa el objeto recibido al servicio para guardarlo en la BD.
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
        // 201 Created o 400 Bad Request si hay error
    }

    // PUT actualizar existente
    @PutMapping("/{id}") // Actualiza una tecnología existente.
    public ResponseEntity<Technology> updateTechnology(@PathVariable Integer id, @RequestBody Technology technology) {
        Technology updated = technologyService.updateTechnology(id, technology); // Llama al servicio para actualizar.
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }// 200 OK si existe o 404 si no existe

    // DELETE
    @DeleteMapping("/{id}") // Elimina una tecnología por su id.
    public ResponseEntity<Void> deleteTechnology(@PathVariable Integer id) {
        Technology tech = technologyService.getTechnologyById(id);
        if (tech == null)// Verifica si existe.
            return ResponseEntity.notFound().build();
        technologyService.deleteTechnology(id);
        return ResponseEntity.noContent().build();
    } // 204 No Content tras eliminar o 404 si no existe
}
