package com.Numerify.inventory.model;

import com.Numerify.inventory.dto.DepartmentDto;
import com.Numerify.inventory.service.ItemService;
import com.Numerify.inventory.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENT")
public class Department {

    @Id
   @Column(name = "DEPT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME", nullable = false,unique = true)
    private String departmentName;

    @OneToOne
    @JoinColumn(name = "MANAGER_ID")
    private Manager manager;

    @OneToMany(mappedBy = "department",cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Item> items = new ArrayList<>();

    public Department(DepartmentDto departmentDto){
        id = departmentDto.getId();
        departmentName = departmentDto.getDepartmentName();
    }

    public Department(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime + ((departmentName == null) ? 0 : departmentName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Department))
            return false;
        Department other = (Department) obj;
        if (id != other.id)
            return false;
        if (departmentName == null) {
            if (other.departmentName != null)
                return false;
        } else if (!departmentName.equals(other.departmentName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Department[id= " + id + ", name= " + departmentName + ", Manager = " + getManager().getName() + "]";
    }

}