# Inventory-Management
This is an Inventory Management project built in Java. It uses Spring and Hibernate while implementing REST API. 

The calls can be made to create an item, manager or a department.
The manager and the department; hold a One-to-One relationship whereas the department and items hold a One-to-Many relationship. The relationships are specified using Hibernate Mapping. 
MySQL is used as the database here and all the create calls make an entry into the local MySQL database using Hibernate.
Spring is used to ensure all the classes as loosely coupled and it also enables us to develop applications using POJOs.
