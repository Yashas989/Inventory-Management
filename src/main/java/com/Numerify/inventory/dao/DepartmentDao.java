package com.Numerify.inventory.dao;

import com.Numerify.inventory.model.Department;
import com.Numerify.inventory.model.Item;

import java.util.List;

public interface DepartmentDao {

    void saveDepartment(Department department);

    void deleteAllDepartments();

    void deleteDepartmentById(int id);

    List<Department> listAllDepartments();
//
//    List<Item> getItems();
//
//    void setItems(List<Item> items);

    Department findById(int id);

    void updateDepartment(Department department);

}
