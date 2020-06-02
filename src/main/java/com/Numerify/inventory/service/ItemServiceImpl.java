package com.Numerify.inventory.service;

import com.Numerify.inventory.dao.ItemDao;
import com.Numerify.inventory.dto.ItemDto;
import com.Numerify.inventory.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private DepartmentService departmentService;

    @Transactional
    public void saveItem(Item item){
        item.setManuDate(new Timestamp(System.currentTimeMillis()));
        itemDao.saveItem(item);
    }

    @Transactional
    public List<Item> listAllItems(){
        return itemDao.listAllItems();
    }

    @Transactional
    public List<ItemDto> listAllItemDtos(){
        List<ItemDto> itemDtos = new ArrayList<>();
        for(Item item : itemDao.listAllItems())
            itemDtos.add(getDtoFromItem(item));
        return itemDtos;
    }

    @Transactional
    public List<ItemDto> getMinimalDtos(List<Item> items){
        List<ItemDto> itemDtos = new ArrayList<>();
        for(Item item : items){
            ItemDto itemDto = new ItemDto();
            itemDto.setId(item.getId());
            itemDto.setItemName(item.getItemName());
            itemDto.setItemPrice(item.getItemPrice());
            itemDto.setItemQuantity(item.getItemQuantity());
            if(itemDto.getItemQuantity()>0)
                item.setIn_stock(true);
            else
                item.setIn_stock(false);
//            itemDto.setManuDate(item.getManuDate());
//            itemDto.setIn_stock(item.isIn_stock());
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }

    @Transactional
    public ItemDto getDtoFromItem(Item item){
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setItemName(item.getItemName());
        itemDto.setItemPrice(item.getItemPrice());
        itemDto.setItemQuantity(item.getItemQuantity());
        if(item.getItemQuantity()>0)
            itemDto.setIn_stock(true);
        else
            itemDto.setIn_stock(false);
        itemDto.setManuDate(item.getManuDate());
        itemDto.setDepartment(departmentService.convertToDto(item.getDepartment()));
        return itemDto;
    }

//    @Transactional
//    public Item convertToItem(ItemDto itemDto){
//        Item item = new Item();
//        item.setId(itemDto.getId());
//        item.setItemName(itemDto.getItemName());
//        item.setItemPrice(itemDto.getItemPrice());
//        item.setItemQuantity(itemDto.getItemQuantity());
//        if(itemDto.getItemQuantity()>0)
//            item.setIn_stock(true);
//        else
//            item.setIn_stock(false);
//        item.setManuDate(itemDto.getManuDate());
//        item.setDepartment(departmentService.convertToDepartment(itemDto.getDepartment()));
//        return item;
//    }

    @Transactional
    public List<Item> convertToItems(List<ItemDto> itemDtos){
        List<Item> items = new ArrayList<>();
        for(ItemDto itemDto : itemDtos){
            Item item = new Item();
            if(itemDto.getId() > 0)
                item.setId(itemDto.getId());
            if(itemDto.getItemName()!=null)
                item.setItemName(itemDto.getItemName());
            if(itemDto.getItemPrice() > 0)
                item.setItemPrice(itemDto.getItemPrice());
            if(itemDto.getItemQuantity() > 0)
                item.setItemQuantity(itemDto.getItemQuantity());
            if(itemDto.getItemQuantity()>0)
                item.setIn_stock(true);
            else
                item.setIn_stock(false);
            if(itemDto.getManuDate()!=null)
                item.setManuDate(itemDto.getManuDate());
            if(itemDto.getDepartment()!=null)
                item.setDepartment(departmentService.convertToDepartment(itemDto.getDepartment()));
            items.add(item);
        }
        return items;
    }

    @Transactional
    public void deleteItemById(int id){
        if(findById(id)!=null)
            itemDao.deleteItemById(id);
        else
            throw new UnsupportedOperationException("Cannot delete item with invalid ID. Please check again.");
    }

    @Transactional
    public void deleteAllItems(){itemDao.deleteItems();}

    @Transactional
    public Item findById(int id){
        if(itemDao.findById(id)!=null)
            return itemDao.findById(id);
        else
            throw new
                    NoSuchElementException("No such element with the Id exists. Please check again.");
    }

    @Transactional
    public ItemDto findItemDtoById(int id){
        if(findById(id)!=null)
            return getDtoFromItem(findById(id));
        else
            throw new NoSuchElementException("Invalid ID given. Please check again.");
    }

    @Transactional
    public List<Item> listAllInStock(){
        return itemDao.listAllInStock();
    }

    @Transactional
    public List<Item> listFromDepartmentId(int dept_id){
        return itemDao.listFromDepartmentId(dept_id);
    }

    public List<ItemDto> listDtoFromDepartmentId(int dept_id){
        List<ItemDto> itemDtos = new ArrayList<>();
        for(Item item : listFromDepartmentId(dept_id))
            itemDtos.add(getDtoFromItem(item));
        return itemDtos;
    }

    @Transactional
    public void updateItem(Item item){
        itemDao.updateItem(item);
    }

    @Transactional
    public ItemDto updateItemDto(ItemDto itemDto){

        if(findById(itemDto.getId())!=null)
        {
            Item item = findById(itemDto.getId());
            if(itemDto.getItemName()!=null)
                item.setItemName(itemDto.getItemName());
            if(itemDto.getItemPrice() > 0)
                item.setItemPrice(itemDto.getItemPrice());
            if(itemDto.getManuDate()!=null)
                item.setManuDate(itemDto.getManuDate());
            if(itemDto.getItemQuantity() > 0)
                item.setItemQuantity(itemDto.getItemQuantity());
            if(itemDto.getItemQuantity()>0)
                item.setIn_stock(true);
            else
                item.setIn_stock(false);
            itemDao.updateItem(item);
            return getDtoFromItem(item);
        }
        else
            throw new NoSuchElementException("Invalid ID for the item. Please check again.");
    }

    @Transactional
    public ItemDto createItemDto(ItemDto itemDto){
        Item item = new Item();
        item.setItemName(itemDto.getItemName());
        item.setItemPrice(itemDto.getItemPrice());
        item.setItemQuantity(itemDto.getItemQuantity());
        if(itemDto.getItemQuantity()>0)
            item.setIn_stock(true);
        else
            item.setIn_stock(false);
        item.setManuDate(new Timestamp(new Date().getTime()));
        item.setDepartment(departmentService.findById(itemDto.getDepartment().getId()));
        itemDao.saveItem(item);
        itemDto.setId(item.getId());
        return itemDto;
    }

}
