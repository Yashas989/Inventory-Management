package com.Numerify.inventory.dao;


import com.Numerify.inventory.model.Item;

import java.util.List;
import java.util.Set;

public interface ItemDao {

    void saveItem(Item item);

    List<Item> listAllItems();

    void deleteItems();

    void deleteItemById(int id);

    Item findById(int id);

    List<Item> listAllInStock();

    List<Item> listFromDepartmentId(int dept_id);

    void updateItem(Item item);

}
