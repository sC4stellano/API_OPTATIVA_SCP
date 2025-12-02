package API.OPTATIVA.demo.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import API.OPTATIVA.demo.Persistence.Model.Technology;
import API.OPTATIVA.demo.Persistence.Repository.TechnologyRepository;
import API.OPTATIVA.demo.Service.TechnologyService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TechnologyServiceImpl implements TechnologyService {
    private final TechnologyRepository technologyRepository;

    @Override
    public List<Technology> getAllTechnologies() {
        return technologyRepository.findAll();
    }

    @Override
    public Technology getTechnologyById(Integer id) {
        return technologyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Technology> getTechnologiesByName(String name) {
        return technologyRepository.findByTechNameContainingIgnoreCase(name);
    }

    @Override
    public Technology createTechnology(Technology technology) {
        return technologyRepository.save(technology);
    }

    @Override
    public Technology updateTechnology(Integer id, Technology technology) {
        Technology existing = technologyRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setTechName(technology.getTechName());
            existing.setProjects(technology.getProjects());
            return technologyRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteTechnology(Integer id) {
        technologyRepository.deleteById(id);
    }
}
