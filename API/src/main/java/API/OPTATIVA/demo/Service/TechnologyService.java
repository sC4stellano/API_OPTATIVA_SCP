package API.OPTATIVA.demo.Service;

import API.OPTATIVA.demo.Persistence.Model.Technology;
import java.util.List;

public interface TechnologyService { // Servicio para la entidad Technology

    public List<Technology> getAllTechnologies();// Obtener todas las tecnologías

    public Technology getTechnologyById(Integer id); // Obtener tecnología por ID

    public List<Technology> getTechnologiesByName(String name); // Obtener tecnologías por nombre

    public Technology createTechnology(Technology technology); // Crear una nueva tecnología

    public Technology updateTechnology(Integer id, Technology technology); // Actualizar una tecnología existente

    public void deleteTechnology(Integer id); // Eliminar una tecnología por ID
}
