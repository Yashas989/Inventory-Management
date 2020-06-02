package com.Numerify.inventory.controller;

import java.util.List;
import java.util.NoSuchElementException;

import com.Numerify.inventory.dto.ManagerDto;
import com.Numerify.inventory.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @GetMapping("managers")
    public List<ManagerDto> listAllManagers(){
        return managerService.listAllDtos();
    }

    @GetMapping("managers/{id}")
    public ManagerDto findByID(@PathVariable("id") int id) {
        try{
            return  managerService.getManagerById(id);
        }
        catch (Exception e){
            throw e;
            //throw new NoSuchElementException("No such manager with id " + id + " exists. Please check again.");
        }
    }

    @DeleteMapping("managers/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id){
        try {
            managerService.deleteManagerById(id);
            return new ResponseEntity<Integer>(id, HttpStatus.OK);
        }
        catch (Exception e){
            throw e;
            //throw new NoSuchElementException("No such manager with id " + id + " exists. Please check again.");
        }
    }

    @PutMapping("managers/manager")
    public ResponseEntity<ManagerDto> updateManager(@RequestBody ManagerDto managerDto){
        try {
            managerService.updateManagerDto(managerDto);
            return new ResponseEntity<ManagerDto>(managerDto, HttpStatus.OK);
        }
        catch (Exception e){
            throw e;
            //throw new UnsupportedOperationException("The data provided to update a field was incorrect. Please check again.");
        }
    }

    @PostMapping("managers/manager")
    public ResponseEntity<?> createManager(@RequestBody ManagerDto managerDto){
        try {
            managerService.createManager(managerDto);
            return new ResponseEntity<ManagerDto>(managerDto, HttpStatus.OK);
        }
        catch(UnsupportedOperationException u){
            throw u;
        }
        catch (Exception e){
            throw new UnsupportedOperationException("There was an error while creating the Manager. Please check again.");
        }
    }

}
