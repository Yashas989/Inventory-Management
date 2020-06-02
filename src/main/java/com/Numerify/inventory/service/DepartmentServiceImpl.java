package com.Numerify.inventory.service;

import com.Numerify.inventory.dao.DepartmentDao;
import com.Numerify.inventory.dto.DepartmentDto;
import com.Numerify.inventory.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ItemService itemService;

    @Transactional
    public void deleteAllDepartments(){departmentDao.deleteAllDepartments();}

    @Transactional
    public void deleteDepartmentById(int id){
        if(departmentDao.findById(id).getItems().isEmpty())
            departmentDao.deleteDepartmentById(id);
        else
            throw new UnsupportedOperationException("The department has items in it. Cannot delete department.");
    }

    @Transactional
    public void saveDepartment(Department department){
        departmentDao.saveDepartment(department);
    }

    @Transactional
    public List<Department> listAllDepartments(){
        return departmentDao.listAllDepartments();
    }

    @Transactional
    public List<DepartmentDto> listAllDtos(){
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        for(Department dept : departmentDao.listAllDepartments())
            departmentDtos.add(convertToDto(dept));
            return departmentDtos;
    }

    @Transactional
    public DepartmentDto convertToDto(Department department)throws NoSuchElementException{
       DepartmentDto departmentDto = new DepartmentDto();
            departmentDto.setId(department.getId());
           departmentDto.setDepartmentName(department.getDepartmentName());
       if(department.getManager()!= null)
           departmentDto.setManager(managerService.getMinimalDto(department.getManager()));
       return departmentDto;
    }

    @Transactional
    public Department convertToDepartment(DepartmentDto departmentDto){
        Department department = new Department();
        if(departmentDto.getId() > 0)
            department.setId(departmentDto.getId());
        else
            throw new NoSuchElementException("Please enter a valid ID for the department.");
        if(departmentDto.getDepartmentName()!=null)
            department.setDepartmentName(departmentDto.getDepartmentName());
        else
            throw new NoSuchElementException("Enter a valid department name.");
        if(departmentDto.getManager()!=null)
            department.setManager(managerService.getManagerFromDto(departmentDto.getManager()));
        else
            throw new NoSuchElementException("Manager does not exist. Please enter a valid manager.");
        if(!departmentDto.getItems().isEmpty())
            department.setItems(itemService.convertToItems(departmentDto.getItems()));
        else
            throw new UnsupportedOperationException("There are no items in the department. Please check again.");
        return department;
    }

    @Transactional
    public DepartmentDto getMinimalDto(Department department){
        DepartmentDto departmentDto = new DepartmentDto();
        if (department.getId() > 0)
            departmentDto.setId(department.getId());
        else
            throw new NoSuchElementException("Please enter a valid ID for the department.");
        if(department.getDepartmentName()!=null)
            departmentDto.setDepartmentName(department.getDepartmentName());
        else
            throw new NoSuchElementException("Enter a valid department name.");
        return departmentDto;
    }

    @Transactional
    public Department findById(int id){

        if(departmentDao.findById(id)!=null)
            return departmentDao.findById(id);
        else
            throw new NoSuchElementException("Please enter a valid ID for the department.");
    }

    @Transactional
    public DepartmentDto findDtoById(int id){
        Department department = departmentDao.findById(id);
        if (department != null)
                return convertToDto(department);
        else
            throw new NoSuchElementException("Please enter a valid ID for the department.");
    }

    @Transactional
    public void updateDepartment(Department department){
        departmentDao.updateDepartment(department);
    }

    @Transactional
    public DepartmentDto updateDepartmentDto(DepartmentDto departmentDto) {
        Department department = findById(departmentDto.getId());
        if (department != null)
        {
            if(departmentDto.getDepartmentName() != null)
                department.setDepartmentName(departmentDto.getDepartmentName());
            if(departmentDto.getManager() != null)
                department.setManager(managerService.findById(departmentDto.getManager().getId()));
            departmentDao.updateDepartment(department);
            return convertToDto(department);
        }
        else
            throw new NoSuchElementException("Please enter a valid ID for the department.");
    }

    @Transactional
    public DepartmentDto createDepartment(DepartmentDto departmentDto){
        Department department = new Department();
        department.setDepartmentName(departmentDto.getDepartmentName());
        if(departmentDto.getManager()!=null)
            department.setManager(managerService.findById(departmentDto.getManager().getId()));
        departmentDao.saveDepartment(department);
        departmentDto.setId(department.getId());
        return departmentDto;
    }

}