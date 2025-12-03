package API.OPTATIVA.demo.Persistence.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Data
@NoArgsConstructor
@Entity
@Table(name = "technologies")
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tech_id", nullable = false, unique = true)
    Integer techId;

    @Column(name = "tech_name", nullable = false, unique = true)
    String techName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "technologies_used_in_projects", joinColumns = {
            @JoinColumn(name = "tecnologia_tecnologia_id", referencedColumnName = "tech_id") }, inverseJoinColumns = {
                    @JoinColumn(name = "projects_project_id", referencedColumnName = "project_id") })
    @JsonIgnore
    List<Project> projects;
}
