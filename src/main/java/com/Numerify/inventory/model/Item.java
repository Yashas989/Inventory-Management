package com.Numerify.inventory.model;

import com.Numerify.inventory.dto.ItemDto;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ITEM")
public class Item {

    @Id
    @Column(name = "ITEM_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String itemName;

    @Column(name = "PRICE", nullable = false)
    private int itemPrice;

    @Column(name = "QUANTITY", nullable = false)
    private int itemQuantity;

    @Column(name = "IN_STOCK", nullable = false)
    private boolean in_stock;

    @Column(name = "MANU_DATE", nullable = false)
    private Timestamp manuDate;

    @ManyToOne()
    @JoinColumn(name = "DEPT_ID")
    private Department department;

    public Item(){}

    public Timestamp getManuDate() {
        return manuDate;
    }

    public void setManuDate(Timestamp manuDate) {
        this.manuDate = manuDate;
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

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

    @Override
    public String toString() {
        return "Item[id= " + id + ", name= " + itemName + ", price= " + itemPrice + ", quantity=" + itemQuantity +
                ", in-stock= " + in_stock + "]";
    }

}
