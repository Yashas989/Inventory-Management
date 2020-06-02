package com.Numerify.inventory.dao;

import com.Numerify.inventory.model.Manager;

import java.util.List;

public interface ManagerDao {

    void saveManager(Manager manager);

    List<Manager> listAllManagers();

    void deleteAllManagers();

    void deleteManagerById(int id);

    Manager findById(int managerId);

    void updateManager(Manager manager);

}
