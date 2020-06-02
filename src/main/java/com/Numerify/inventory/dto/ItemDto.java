package com.Numerify.inventory.dto;

import com.Numerify.inventory.model.Item;
import com.Numerify.inventory.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

public class ItemDto {

    private int id;

    private String itemName;

    private int itemPrice;

    private int itemQuantity;

    private boolean in_stock;

    private Timestamp manuDate;

    private DepartmentDto department;

    @Autowired
    private DepartmentService departmentService;

    public ItemDto(Item item){

    }

    public ItemDto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public boolean isIn_stock() {
        return in_stock;
    }

    public void setIn_stock(boolean in_stock) {
        this.in_stock = in_stock;
    }

    public Timestamp getManuDate() {
        return manuDate;
    }

    public void setManuDate(Timestamp manuDate) {
        this.manuDate = manuDate;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

//    public Department getDepartment(){
//        return departmentService.convertToDepartment(departmentDto);
//    }

    public void setDepartment(DepartmentDto departmentDto) {
        this.department = departmentDto;
    }
}
