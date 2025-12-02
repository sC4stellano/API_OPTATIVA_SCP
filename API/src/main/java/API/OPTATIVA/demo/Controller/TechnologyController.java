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
    @GetMapping
    public ResponseEntity<List<Technology>> getAllTechnologies() {
        List<Technology> techs = technologyService.getAllTechnologies();
        return techs.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(techs);
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<Technology> getTechnologyById(@PathVariable Integer id) {
        Technology tech = technologyService.getTechnologyById(id);
        return tech != null ? ResponseEntity.ok(tech) : ResponseEntity.notFound().build();
    }

    // GET por nombre
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Technology>> getTechnologiesByName(@PathVariable String name) {
        List<Technology> techs = technologyService.getTechnologiesByName(name);
        return techs.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(techs);
    }

    // POST nueva tecnología
    @PostMapping
    public ResponseEntity<Technology> createTechnology(@RequestBody Technology technology) {
        Technology created = technologyService.createTechnology(technology);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT actualizar existente
    @PutMapping("/{id}")
    public ResponseEntity<Technology> updateTechnology(@PathVariable Integer id, @RequestBody Technology technology) {
        Technology updated = technologyService.updateTechnology(id, technology);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable Integer id) {
        Technology tech = technologyService.getTechnologyById(id);
        if (tech == null)
            return ResponseEntity.notFound().build();
        technologyService.deleteTechnology(id);
        return ResponseEntity.noContent().build();
    }
}
