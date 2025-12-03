package API.OPTATIVA.demo.Service;

import java.util.List;

import API.OPTATIVA.demo.Persistence.Model.Developer;

public interface DeveloperService { // Interfaz del servicio para la entidad Developer
    public List<Developer> getAllDevelopers(); // Obtener todos los developers

    public Developer getDeveloperById(Integer id); // Obtener developer por ID

    public List<Developer> getDevelopersByName(String name); // Obtener developers por nombre

    public Developer createDeveloper(Developer developer); // Crear un nuevo developer

    public Developer updateDeveloper(Integer id, Developer developer); // Actualizar un developer existente

    public void deleteDeveloper(Integer id); // Eliminar un developer por ID
}
