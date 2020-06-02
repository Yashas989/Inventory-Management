package com.Numerify.inventory.service;

import com.Numerify.inventory.dto.DepartmentDto;
import com.Numerify.inventory.model.Department;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Component
public interface DepartmentService {

    @Transactional
    void saveDepartment(Department department);

    @Transactional
    void deleteAllDepartments();

    @Transactional
    void deleteDepartmentById(int id);

    @Transactional
    List<Department> listAllDepartments();

    @Transactional
    DepartmentDto convertToDto(Department department);

   @Transactional
   DepartmentDto getMinimalDto(Department department);

    @Transactional
    Department findById(int id);

    @Transactional
    DepartmentDto findDtoById(int id);

    @Transactional
    Department convertToDepartment(DepartmentDto departmentDto);

    @Transactional
    List<DepartmentDto> listAllDtos();

    @Transactional
    void updateDepartment(Department department);

    @Transactional
    DepartmentDto updateDepartmentDto(DepartmentDto departmentDto);

    @Transactional
    DepartmentDto createDepartment(DepartmentDto departmentDto);

}
