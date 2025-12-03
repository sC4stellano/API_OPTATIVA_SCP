package API.OPTATIVA.demo.Persistence.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data // Genera getters, setters, toString, equals, y hashCode
@NoArgsConstructor // Genera un constructor sin argumentos
@Entity // Marca esta clase como una entidad JPA
@Table(name = "developers") // Mapea esta entidad a la tabla "developers"
public class Developer {
    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el valor de la clave primaria automáticamente
    @Column(name = "dev_id") // Mapea este campo a la columna "dev_id"
    private Integer id; // Identificador único del developer

    @Column(name = "devname") // Mapea este campo a la columna "devname"
    private String name;

    @Column(name = "dev_surname") // Mapea este campo a la columna "dev_surname"
    private String surname;

    @Column(name = "email") // Mapea este campo a la columna "email"
    private String email;

    @Column(name = "linkedin_url") // Mapea este campo a la columna "linkedin_url"
    private String linUrl;

    @Column(name = "github_url") // Mapea este campo a la columna "github_url"
    private String gitUrl;

    @ManyToMany(fetch = FetchType.EAGER) // Relación muchos a muchos con Project
    @JoinTable(name = "developers_worked_on_projects", joinColumns = @JoinColumn(name = "developer_dev_id", referencedColumnName = "dev_id"), inverseJoinColumns = @JoinColumn(name = "projects_project_id", referencedColumnName = "project_id"))
    // Tabla intermedia para la relación muchos a muchos entre developers y projects
    private List<Project> projects; // Proyectos asociados al developer
}
