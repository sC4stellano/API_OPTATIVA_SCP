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

@Data
@NoArgsConstructor
@Entity
@Table(name = "developers")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dev_id")
    private Integer id;

    @Column(name = "devname")
    private String name;

    @Column(name = "dev_surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "linkedin_url")
    private String linUrl;

    @Column(name = "github_url")
    private String gitUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "developers_worked_on_projects", joinColumns = @JoinColumn(name = "developer_dev_id", referencedColumnName = "dev_id"), inverseJoinColumns = @JoinColumn(name = "projects_project_id", referencedColumnName = "project_id"))
    private List<Project> projects;
}
