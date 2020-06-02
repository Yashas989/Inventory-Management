package com.Numerify.inventory.service;

import com.Numerify.inventory.dao.ManagerDao;
import com.Numerify.inventory.dto.ManagerDto;
import com.Numerify.inventory.model.Department;
import com.Numerify.inventory.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    DepartmentService departmentService;

    @Transactional
    public void saveManager(Manager manager){
        managerDao.saveManager(manager);
    }

    @Transactional
    public List<ManagerDto> listAllDtos(){
        List<ManagerDto> managerDtos = new ArrayList<>();
        for(Manager man : managerDao.listAllManagers()){
            managerDtos.add(getDtoFromManager(man));
        }
        return managerDtos;
    }

    @Transactional
    public ManagerDto getMinimalDto(Manager manager){
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        if(manager.getName()!=null)
            managerDto.setName(manager.getName());
        if(manager.getAddress()!=null)
            managerDto.setAddress(manager.getAddress());
        return managerDto;
    }

    @Transactional
    public ManagerDto getDtoFromManager(Manager manager){
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        if(manager.getName()!=null)
            managerDto.setName(manager.getName());
        if(manager.getAddress()!=null)
            managerDto.setAddress(manager.getAddress());
        if(manager.getDepartment()!=null)
            managerDto.setDepartment(departmentService.getMinimalDto(manager.getDepartment()));
        return managerDto;
    }

    @Transactional
    public Manager getManagerFromDto(ManagerDto managerDto){
        return findById(managerDto.getId());
    }

    @Transactional
    public List<Manager> listAllManagers(){
        return managerDao.listAllManagers();
    }

    @Transactional
    public void deleteAllManagers(){
        managerDao.deleteAllManagers();
    }

    @Transactional
    public void deleteManagerById(int id){
        if(findById(id).getDepartment()!=null) {
            Department department = departmentService.findById(findById(id).getDepartment().getId());
            department.setManager(null);
            managerDao.deleteManagerById(id);
        }
        else if(findById(id)== null)
            throw new UnsupportedOperationException("Invalid ID for manager. Please check again.");
        else
            managerDao.deleteManagerById(id);
    }

    @Transactional
    public Manager findById(int id){
        Manager manager = managerDao.findById(id);
        if(manager!=null)
            return manager;
        else
            throw new NoSuchElementException("Please enter a valid ID in the field. Manager not found.");
    }

    @Transactional
    public void updateManager(Manager manager){
        managerDao.updateManager(manager);
    }

    @Transactional
    public ManagerDto updateManagerDto(ManagerDto managerDto){
        Manager manager = findById(managerDto.getId());
        if(manager!=null)
        {
            if(managerDto.getName()!=null)
                manager.setName(managerDto.getName());
            if(managerDto.getAddress()!=null)
                manager.setAddress(managerDto.getAddress());
            if(managerDto.getDepartment()!=null)
                manager.setDepartment(departmentService.findById(managerDto.getDepartment().getId()));
            managerDao.updateManager(manager);
            return getDtoFromManager(manager);
        }
        else
            throw new NoSuchElementException("Invalid ID for manager. Please check again.");
    }

    @Transactional
    public ManagerDto getManagerById(int id){
        Manager manager = managerDao.findById(id);
        if(manager!=null)
            return getDtoFromManager(manager);
        else
            throw new NoSuchElementException("Please enter a valid ID in the field. Manager not found.");
    }

    public ManagerDto createManager(ManagerDto managerDto){
        Manager manager = new Manager();
        if(managerDto.getName()!=null)
            manager.setName(managerDto.getName());
        else
            throw new UnsupportedOperationException("Enter a valid name for the manager.");
        if(managerDto.getAddress()!=null)
            manager.setAddress(managerDto.getAddress());
        else
            manager.setAddress("");
        managerDao.saveManager(manager);
        managerDto.setId(manager.getId());
        return managerDto;
    }

}
