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
    @GetMapping
    public ResponseEntity<List<Status>> getAllStatus() {
        List<Status> statuses = statusService.getAllStatus();
        return statuses.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(statuses);
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable Integer id) {
        Status status = statusService.getStatusById(id);
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.notFound().build();
    }

    // GET por nombre
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Status>> getStatusByName(@PathVariable String name) {
        List<Status> statuses = statusService.getStatusByName(name);
        return statuses.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(statuses);
    }

    // POST nueva status
    @PostMapping
    public ResponseEntity<Status> createStatus(@RequestBody Status status) {
        Status created = statusService.createStatus(status);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT actualizar existente
    @PutMapping("/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable Integer id, @RequestBody Status status) {
        Status updated = statusService.updateStatus(id, status);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Integer id) {
        Status status = statusService.getStatusById(id);
        if (status == null)
            return ResponseEntity.notFound().build();
        statusService.deleteStatus(id);
        return ResponseEntity.noContent().build();
    }
}
