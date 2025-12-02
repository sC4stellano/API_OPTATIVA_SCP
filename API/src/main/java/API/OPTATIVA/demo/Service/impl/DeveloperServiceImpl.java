package API.OPTATIVA.demo.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import API.OPTATIVA.demo.Persistence.Model.Developer;
import API.OPTATIVA.demo.Persistence.Repository.DeveloperRepository;
import API.OPTATIVA.demo.Service.DeveloperService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository developerRepository;

    @Override
    public List<Developer> getAllDevelopers() {
        return developerRepository.findAll();
    }

    @Override
    public Developer getDeveloperById(Integer id) {
        return developerRepository.findById(id).orElse(null); // devuelve null si no existe
    }

    @Override
    public List<Developer> getDevelopersByName(String name) {
        return developerRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Developer createDeveloper(Developer developer) {
        return developerRepository.save(developer);
    }

    @Override
    public Developer updateDeveloper(Integer id, Developer developer) {
        Developer existing = developerRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(developer.getName());
            existing.setSurname(developer.getSurname());
            existing.setEmail(developer.getEmail());
            existing.setLinUrl(developer.getLinUrl());
            existing.setGitUrl(developer.getGitUrl());
            return developerRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteDeveloper(Integer id) {
        developerRepository.deleteById(id);
    }
}
