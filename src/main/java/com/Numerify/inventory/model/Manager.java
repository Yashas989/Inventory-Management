package com.Numerify.inventory.model;

import com.Numerify.inventory.dto.ManagerDto;

import javax.persistence.*;

@Entity
@Table(name = "MANAGER")
public class Manager {

    @Id
    @Column(name = "MANAGER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "manager")
    private Department department;

    public Manager(ManagerDto managerDto){
        id = managerDto.getId();
        name = managerDto.getName();
        address = managerDto.getAddress();
        //department = managerDto.getDepartment();
    }

    public Manager(){}

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Manager))
            return false;
        Manager other = (Manager) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Manager[id= " + id + ", name= " + name    + ", address = " + address + ", department = "
                + getDepartment().getDepartmentName() + "]";
    }
}
