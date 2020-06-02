package com.Numerify.inventory;

import com.Numerify.inventory.config.MvcConfiguration;
import com.Numerify.inventory.model.Department;
import com.Numerify.inventory.model.Item;
import com.Numerify.inventory.model.Manager;
import com.Numerify.inventory.service.DepartmentService;
import com.Numerify.inventory.service.ItemService;
import com.Numerify.inventory.service.ManagerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        AbstractApplicationContext context =new AnnotationConfigApplicationContext(MvcConfiguration.class);

        ManagerService managerService =(ManagerService) context.getBean("managerService");
        DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");
        ItemService itemService = (ItemService) context.getBean("itemService");

        itemService.deleteAllItems();
        managerService.deleteAllManagers();
        departmentService.deleteAllDepartments();

        Manager manager = new Manager();
        manager.setName("Ramesh");
        manager.setAddress("Kammanahalli, Bangalore");
        managerService.saveManager(manager);

        Department department = new Department();
        department.setDepartmentName("Sanitation");
        department.setManager(manager);
//        departmentService.saveDepartment(department);

        Item item1 = new Item();
        item1.setItemName("Sanitizers");
        item1.setIn_stock(true);
        item1.setItemPrice(300);
        item1.setItemQuantity(10);
        item1.setManuDate(new Timestamp(System.currentTimeMillis()));

        Item item2 = new Item();
        item2.setItemName("Tissues");
        item2.setIn_stock(true);
        item2.setItemPrice(50);
        item2.setItemQuantity(20);

        item2.setManuDate(new Timestamp(System.currentTimeMillis()));

        Item item3 = new Item();
        item3.setItemName("Toilet Paper");
        item3.setIn_stock(false);
        item3.setItemPrice(50);
        item3.setItemQuantity(0);
        item3.setManuDate(new Timestamp(System.currentTimeMillis()));

        item1.setDepartment(department);
        item2.setDepartment(department);
        item3.setDepartment(department);


        Manager manager2 = new Manager();
        manager2.setName("Suresh");
        manager2.setAddress("MG Road, Bangalore");
        managerService.saveManager(manager2);

        Department department2 = new Department();
        department2.setDepartmentName("Stationery");
        department2.setManager(manager2);
//        departmentService.saveDepartment(department);

        Item item4 = new Item();
        item4.setItemName("Pencils");
        item4.setIn_stock(true);
        item4.setItemPrice(50);
        item4.setItemQuantity(10);
        item4.setManuDate(new Timestamp(System.currentTimeMillis()));

        Item item5 = new Item();
        item5.setItemName("Pens");
        item5.setIn_stock(true);
        item5.setItemPrice(100);
        item5.setItemQuantity(20);

        item5.setManuDate(new Timestamp(System.currentTimeMillis()));

        Item item6 = new Item();
        item6.setItemName("Crayons");
        item6.setIn_stock(false);
        item6.setItemPrice(50);
        item6.setItemQuantity(0);
        item6.setManuDate(new Timestamp(System.currentTimeMillis()));

        item4.setDepartment(department2);
        item5.setDepartment(department2);
        item6.setDepartment(department2);

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        List<Item> items1 = new ArrayList<>();
        items1.add(item4);
        items1.add(item5);
        items1.add(item6);


        department.setItems(items);
        departmentService.saveDepartment(department);
        department2.setItems(items1);
        departmentService.saveDepartment(department2);


//        itemService.saveItem(item1);
//        itemService.saveItem(item2);
//        itemService.saveItem(item3);

//        itemService.deleteItemById(13);
//        departmentService.deleteDepartmentById(2);
//        managerService.deleteManagerById(61);

        System.out.println("All items : ");

        List<Item> items11 = itemService.listAllItems();
        for(Item item : items11)
            System.out.println(item);

        System.out.println("\n All items in STOCK : ");

        List<Item> itemss = itemService.listAllInStock();
        for(Item item : itemss)
            System.out.println(item);
        System.out.println();

        System.out.println("Departments: ");
        List<Department> departments = departmentService.listAllDepartments();
        for(Department dept : departments)
            System.out.println(dept);

        System.out.println();

        System.out.println("Managers: ");
        List<Manager> managers = managerService.listAllManagers();
        for(Manager man : managers)
            System.out.println(man);

        context.close();

    }

}