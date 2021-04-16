package library.service;

import library.persistence.dao.DaoRole;
import library.persistence.model.Role;

public class ServiceRole {

    private DaoRole roleDao = new DaoRole();

    public Role getRoleById(int id) {
        return roleDao.read(id);
    }
}
