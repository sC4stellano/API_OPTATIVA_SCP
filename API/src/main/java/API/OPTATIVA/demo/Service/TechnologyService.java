package API.OPTATIVA.demo.Service;

import API.OPTATIVA.demo.Persistence.Model.Technology;
import java.util.List;

public interface TechnologyService {

    public List<Technology> getAllTechnologies();

    public Technology getTechnologyById(Integer id);

    public List<Technology> getTechnologiesByName(String name);

    public Technology createTechnology(Technology technology);

    public Technology updateTechnology(Integer id, Technology technology);

    public void deleteTechnology(Integer id);
}
