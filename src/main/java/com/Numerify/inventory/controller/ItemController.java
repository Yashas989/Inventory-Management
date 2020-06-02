package com.Numerify.inventory.controller;

import com.Numerify.inventory.dto.ItemDto;
import com.Numerify.inventory.service.DepartmentService;
import com.Numerify.inventory.service.ItemService;
import org.elasticsearch.common.inject.CreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    DepartmentService departmentService;

    @GetMapping("items")
    public List<ItemDto> getItems(){
        return itemService.listAllItemDtos();
    }

    @GetMapping("items/{id}")
    public ItemDto getItem(@PathVariable("id") int id){
        try {
            return itemService.findItemDtoById(id);
        }
        catch (Exception e){
            throw e;
            //throw new NoSuchElementException("No such item with id " + id + " exists. Please check again.");
        }
    }

    @GetMapping("items/dept/{id}")
    public List<ItemDto> getItemsFromDepartment(@PathVariable("id") int dept_id){
       try{
           if(departmentService.findById(dept_id)!=null){
               if(!itemService.listDtoFromDepartmentId(dept_id).isEmpty())
                   return itemService.listDtoFromDepartmentId(dept_id);
               else
                   throw new NoSuchElementException("There are no items in the department.");
           }
           else
               throw new NoSuchElementException("There is no department with id " + dept_id + ". Please check again.");
       }
       catch(Exception e){
           throw e;
       }

    }

    @DeleteMapping("items/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id){
        try {
            itemService.deleteItemById(id);
            return new ResponseEntity<Integer>(id, HttpStatus.OK);
        }
        catch (Exception e){
            throw e;
            //throw new NoSuchElementException("No such item with id " + id + " exists. Please check again.");
        }
    }

    @PutMapping("items/item")
    public ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto itemDto){
       try{
           itemService.updateItemDto(itemDto);
           return new ResponseEntity<ItemDto>(itemDto, HttpStatus.OK);
       }
       catch (Exception e){
           throw e;
           //throw new UnsupportedOperationException("The data provided to update a field was incorrect. Please check again.");
       }
    }

    @PostMapping("items/item")
    public ResponseEntity<?> createItem(@RequestBody ItemDto itemDto){
       try{
           itemService.createItemDto(itemDto);
           return new ResponseEntity<>(itemDto, HttpStatus.OK);
       }
       catch (Exception e){
           throw e;
           //throw new UnsupportedOperationException("There was an error while creating the Item. Please check again.");
       }
    }

}
