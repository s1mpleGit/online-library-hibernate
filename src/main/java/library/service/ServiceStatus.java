package library.service;

import library.persistence.dao.DaoStatus;
import library.persistence.model.Status;

public class ServiceStatus {

    private DaoStatus statusDao = new DaoStatus();

    public Status getStatusById(int id) {
        return statusDao.read(id);
    }
}
