package API.OPTATIVA.demo.Persistence.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, equals, y hashCode
@NoArgsConstructor // Genera un constructor sin argumentos
@Entity // Marca esta clase como una entidad JPA
@Table(name = "status") // Mapea esta entidad a la tabla "status"
public class Status {
    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el valor de la clave primaria automáticamente
    @Column(name = "status_id", nullable = false, unique = true) // Mapea este campo a la columna "status_id"
    Integer statusId;

    @Column(name = "status_name", nullable = false, unique = true) // Mapea este campo a la columna "status_name"
    String statusName;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY) // Relación uno a muchos con Project
    @JsonIgnore // Evita serialización infinita
    private List<Project> products;
}
