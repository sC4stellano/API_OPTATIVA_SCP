package API.OPTATIVA.demo.Persistence.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data // Genera getters, setters, toString, equals, y hashCode
@NoArgsConstructor // Genera un constructor sin argumentos
@Entity // Marca esta clase como una entidad JPA
@Table(name = "projects") // Mapea esta entidad a la tabla "projects"
public class Project {
    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el valor de la clave primaria automáticamente
    @Column(name = "project_id") // Mapea este campo a la columna "project_id"
    private Integer id;

    @Column(name = "project_name") // Mapea este campo a la columna "project_name"
    private String name;

    @Column(name = "description", columnDefinition = "LONGTEXT") // Mapea este campo a la columna "description"
    private String description;

    @Column(name = "start_date") // Mapea este campo a la columna "start_date"
    private LocalDate starDate;

    @Column(name = "end_date") // Mapea este campo a la columna "end_date"
    private LocalDate endDate;

    // Usamos demo_url como URL principal del proyecto
    @Column(name = "demo_url") // Mapea este campo a la columna "demo_url"
    private String demoUrl;

    @Column(name = "picture") // Mapea este campo a la columna "picture"
    private String picture;

    @ManyToOne(fetch = FetchType.EAGER) // Muchos proyectos pueden tener un mismo estado
    @JoinColumn(name = "status_status_id", referencedColumnName = "status_id") // Clave foránea al estado
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // Evita problemas de serialización
    private Status status; // Estado del proyecto

    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY) // Relación muchos a muchos con Developer
    @JsonIgnore // Evita serialización infinita
    private List<Developer> developersWorking;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY) // Relación muchos a muchos con Technology
    @JsonIgnore // Evita serialización infinita
    private List<Technology> technologiesUsed;
}
