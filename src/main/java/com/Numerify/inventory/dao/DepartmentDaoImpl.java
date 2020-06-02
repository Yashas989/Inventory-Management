package com.Numerify.inventory.dao;

import com.Numerify.inventory.model.Department;
import com.Numerify.inventory.model.Manager;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Repository("departmentDao")
@Transactional
public class DepartmentDaoImpl extends AbstractDao implements DepartmentDao {

    public void saveDepartment(Department department){
        persist(department);
    }

    public List<Department> listAllDepartments(){
        Criteria criteria = getSession().createCriteria(Department.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Department> departments = (List<Department>) criteria.list();
//        for(Department department : departments)
//            department.setManager(null);
        return departments;
    }

    public void deleteAllDepartments(){
        List<Department> instances = getSession().createCriteria(Department.class).list();
        for(Department dept : instances){
            getSession().delete(dept);
        }
//        getSession().getTransaction().commit();
    }


    public void deleteDepartmentById(int id) {
        Department department = (Department) getSession().createCriteria(Department.class).add(Restrictions.eq("id", id)).uniqueResult();
        getSession().delete(department);
    }

    public Department findById(int id) {
        Criteria criteria = getSession().createCriteria(Department.class).add(Restrictions.eq("id", id));
        return (Department) criteria.uniqueResult();
    }

    public void updateDepartment(Department department){
        getSession().update(department);
    }

}
