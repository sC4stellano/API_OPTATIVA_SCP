package API.OPTATIVA.demo.Service;

import java.util.List;

import API.OPTATIVA.demo.Persistence.Model.Developer;

public interface DeveloperService {
    public List<Developer> getAllDevelopers();

    public Developer getDeveloperById(Integer id);

    public List<Developer> getDevelopersByName(String name);

    public Developer createDeveloper(Developer developer);

    public Developer updateDeveloper(Integer id, Developer developer);

    public void deleteDeveloper(Integer id);
}
