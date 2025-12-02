package API.OPTATIVA.demo.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.stereotype.Service;
import API.OPTATIVA.demo.Persistence.Model.Status;
import API.OPTATIVA.demo.Persistence.Repository.StatusRepository;
import API.OPTATIVA.demo.Service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    @Override
    public Status getStatusById(Integer id) {
        return statusRepository.findById(id).orElse(null);
    }

    @Override
    public List<Status> getStatusByName(String name) {
        return statusRepository.findByStatusNameContainingIgnoreCase(name);
    }

    @Override
    public Status createStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Status updateStatus(Integer id, Status status) {
        Status existing = statusRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setStatusName(status.getStatusName());
            return statusRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteStatus(Integer id) {
        statusRepository.deleteById(id);

    }
}