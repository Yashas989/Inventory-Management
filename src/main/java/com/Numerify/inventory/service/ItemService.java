package com.Numerify.inventory.service;

import com.Numerify.inventory.dto.ItemDto;
import com.Numerify.inventory.model.Item;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Component
public interface ItemService {

    @Transactional
    void saveItem(Item item);

    @Transactional
    void deleteAllItems();

    @Transactional
    List<Item> listAllItems();

    @Transactional
    public List<Item> listFromDepartmentId(int dept_id);

    @Transactional
    public List<ItemDto> listDtoFromDepartmentId(int dept_id);

    @Transactional
    void deleteItemById(int id);

    @Transactional
    Item findById(int id);

    @Transactional
    ItemDto findItemDtoById(int id);

    @Transactional
    ItemDto getDtoFromItem(Item item);

    @Transactional
    List<Item> listAllInStock();

//    @Transactional
//    Item convertToItem(ItemDto itemDto);

    @Transactional
    List<Item> convertToItems(List<ItemDto> itemDtos);

    @Transactional
    List<ItemDto> listAllItemDtos();

    @Transactional
    List<ItemDto> getMinimalDtos(List<Item> items);

    @Transactional
    void updateItem(Item item);

    @Transactional
    ItemDto updateItemDto(ItemDto itemDto);

    @Transactional
    ItemDto createItemDto(ItemDto itemDto);

}
