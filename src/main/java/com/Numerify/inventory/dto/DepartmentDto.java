package com.Numerify.inventory.dto;

import com.Numerify.inventory.model.Department;
import com.Numerify.inventory.service.DepartmentService;

import java.util.List;

public class DepartmentDto {

    private int id;

    private String departmentName;

    private ManagerDto managerDto;

    private List<ItemDto> items;

    private DepartmentService departmentService;

    public DepartmentDto(Department department){
        departmentService.convertToDto(department);
    }

    public DepartmentDto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public ManagerDto getManager() {
        return managerDto;
    }

    public void setManager(ManagerDto managerDto) {
        this.managerDto = managerDto;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> itemDtos) {
        this.items = itemDtos;
    }
}
