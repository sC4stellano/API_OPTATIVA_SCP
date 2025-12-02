package API.OPTATIVA.demo.Service;

import java.util.List;
import API.OPTATIVA.demo.Persistence.Model.Status;

public interface StatusService {
    List<Status> getAllStatus();

    Status getStatusById(Integer id);

    List<Status> getStatusByName(String name);

    Status createStatus(Status status);

    Status updateStatus(Integer id, Status status);

    void deleteStatus(Integer id);
}