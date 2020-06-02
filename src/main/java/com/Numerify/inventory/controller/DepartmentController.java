package com.Numerify.inventory.controller;

import com.Numerify.inventory.dto.DepartmentDto;
import com.Numerify.inventory.dto.ItemDto;
import com.Numerify.inventory.service.DepartmentService;
import com.Numerify.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    ItemService itemService;

    @GetMapping("departments")
    public List<DepartmentDto> listAllDepartments(){
       return  departmentService.listAllDtos();
    }

    @GetMapping("departments/{id}")
    public DepartmentDto findDepartmentById(@PathVariable("id") int id){
        try{
            return  departmentService.findDtoById(id);
        }
        catch (Exception e){
            throw e;
            //throw new NoSuchElementException("No such department with id " + id + " exists. Please check again.");
        }
    }

//    @GetMapping("departments/{id}/items")
//    public List<ItemDto> getItemsByDepartment(@PathVariable("id") int dept_id){
//
//        try{
//            if(!itemService.listDtoFromDepartmentId(dept_id).isEmpty())
//                return itemService.listDtoFromDepartmentId(dept_id);
//            else
//                throw new Exception();
//        }
//        catch(Exception e){
//            throw e;
//            //throw new NoSuchElementException("No such department with the id: " + dept_id + " exists or the department is not mapped with any item. Please check again.");
//        }
//
////        DepartmentDto departmentDto = departmentService.findDtoById(id);
////        return departmentDto.getItems();
//    }

    @DeleteMapping("departments/{id}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable("id") int id){
        try {
            departmentService.deleteDepartmentById(id);
            return new ResponseEntity<Integer>(id, HttpStatus.OK);
        }
        catch (Exception e){
            throw e;
            //throw new NoSuchElementException("No such department with id " + id + " exists. Please check again.");
        }
    }

    @PutMapping("departments/department")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto){
        try {
            departmentDto = departmentService.updateDepartmentDto(departmentDto);
            return new ResponseEntity<DepartmentDto>(departmentDto, HttpStatus.OK);
        }
        catch (Exception e){
            //throw new UnsupportedOperationException("The data provided to update a field was incorrect. Please check again.");
            throw e;
        }
    }

    @PostMapping("departments/department")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        try {
            departmentDto = departmentService.createDepartment(departmentDto);
            return new ResponseEntity<DepartmentDto>(departmentDto, HttpStatus.OK);
        }
        catch (Exception e){
            //throw new UnsupportedOperationException("There was an error while creating the Department. Please check again.");
            throw e;
        }
    }

}
