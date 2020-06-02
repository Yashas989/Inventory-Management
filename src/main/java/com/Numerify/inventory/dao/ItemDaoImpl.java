package com.Numerify.inventory.dao;


import com.Numerify.inventory.model.Department;
import com.Numerify.inventory.model.Item;
import com.Numerify.inventory.service.DepartmentService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("itemDao")
@Transactional
public class ItemDaoImpl extends AbstractDao implements ItemDao {

    @Autowired
    DepartmentService departmentService;

    public void saveItem(Item item){
        persist(item);
    }

    public List<Item> listAllItems(){
        Criteria criteria = getSession().createCriteria(Item.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Item> items =  (List<Item>) criteria.list();
//        for(Item item: items)
//            item.setDepartment(null);
        return items;
    }

    public void deleteItemById(int id) {
        Item item = (Item) getSession().createCriteria(Item.class).add(Restrictions.eq("id", id)).uniqueResult();
        delete(item);
    }

    public void deleteItems(){
        List<Item> instances = getSession().createCriteria(Item.class).list();
        for(Item item : instances){
            delete(item);
        }
    }

    public Item findById(int id){
        Criteria criteria = getSession().createCriteria(Item.class).add(Restrictions.eq("id", id));
        return (Item) criteria.uniqueResult();
    }

    public List<Item> listAllInStock(){
        Criteria criteria = getSession().createCriteria(Item.class).add(Restrictions.eq("in_stock", true));
        return (List<Item>) criteria.list();
    }

    public List<Item> listFromDepartmentId(int dept_id){
        Criteria criteria = getSession().createCriteria(Item.class).add(Restrictions.eq("department.id", dept_id));
        return (List<Item>) criteria.list();
    }

    public void updateItem(Item item){
        getSession().update(item);
    }

}
