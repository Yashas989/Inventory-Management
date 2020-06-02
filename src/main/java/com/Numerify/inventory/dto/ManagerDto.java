package com.Numerify.inventory.dto;

import com.Numerify.inventory.model.Manager;
import com.Numerify.inventory.service.ManagerService;

public class ManagerDto {

    private int id;

    private String name;

    private String address;

    private DepartmentDto department;

    private ManagerService managerService;

    public ManagerDto(Manager manager){

    }

    public ManagerDto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }
}
