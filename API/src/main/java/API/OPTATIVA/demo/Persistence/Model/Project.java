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

@Data
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer id;

    @Column(name = "project_name")
    private String name;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "start_date")
    private LocalDate starDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    // Usamos demo_url como URL principal del proyecto
    @Column(name = "demo_url")
    private String demoUrl;

    @Column(name = "picture")
    private String picture;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_status_id", referencedColumnName = "status_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Status status;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Developer> developersWorking;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Technology> technologiesUsed;
}
