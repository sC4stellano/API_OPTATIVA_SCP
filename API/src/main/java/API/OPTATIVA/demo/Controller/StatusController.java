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

import API.OPTATIVA.demo.Persistence.Model.Status;
import API.OPTATIVA.demo.Service.StatusService;
import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/status")
@AllArgsConstructor
public class StatusController {
    private final StatusService statusService;

    // GET todas las status
    @GetMapping // Devuelve todas las status.
    public ResponseEntity<List<Status>> getAllStatus() { // Llama al servicio para obtener la lista.
        List<Status> statuses = statusService.getAllStatus(); // obtiene la lista
        return statuses.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(statuses);
    } // 200 OK con datos o 204 si no hay datos

    // GET por ID
    @GetMapping("/{id}") // Devuelve una status por su id.
    public ResponseEntity<Status> getStatusById(@PathVariable Integer id) {
        Status status = statusService.getStatusById(id); // Busca la status por id en la capa de servicio.
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.notFound().build();
    } // 200 OK si existe o 404 si no existe

    // GET por nombre
    @GetMapping("/search/{name}") // Devuelve las status cuyo nombre contenga 'name'.
    public ResponseEntity<List<Status>> getStatusByName(@PathVariable String name) {
        List<Status> statuses = statusService.getStatusByName(name); // obtiene la lista
        return statuses.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(statuses);
    } // 200 OK con datos o 204 si no hay datos

    // POST nueva status
    @PostMapping // Crea una nueva status a partir del JSON recibido en el cuerpo.
    public ResponseEntity<Status> createStatus(@RequestBody Status status) { // Recibe la status en el cuerpo.
        Status created = statusService.createStatus(status);
        // Pasa el objeto recibido al servicio para guardarlo en la BD.
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
        // 201 Created o 400 Bad Request si hay error
    }

    // PUT actualizar existente
    @PutMapping("/{id}") // Actualiza una status existente.
    public ResponseEntity<Status> updateStatus(@PathVariable Integer id, @RequestBody Status status) {
        Status updated = statusService.updateStatus(id, status); // Llama al servicio para actualizar.
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        // 200 OK si existe o 404 si no existe
    }

    // DELETE
    @DeleteMapping("/{id}") // Elimina una status por su id.
    public ResponseEntity<Void> deleteStatus(@PathVariable Integer id) {
        Status status = statusService.getStatusById(id); // Verifica si existe.
        if (status == null)
            return ResponseEntity.notFound().build();
        statusService.deleteStatus(id);
        return ResponseEntity.noContent().build(); // 204 si se eliminó o 404 si no existe
    }
}
