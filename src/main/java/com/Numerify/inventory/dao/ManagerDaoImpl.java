package com.Numerify.inventory.dao;

import com.Numerify.inventory.dto.ManagerDto;
import com.Numerify.inventory.model.Manager;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Repository("managerDao")
@Transactional
public class ManagerDaoImpl extends AbstractDao implements ManagerDao{


    public void deleteAllManagers(){
        List<Manager> instances = getSession().createCriteria(Manager.class).list();
        for(Manager man : instances){
            delete(man);
        }
    }

    public void saveManager(Manager manager){
        persist(manager);
    }

    public List<Manager> listAllManagers(){
        Criteria criteria = getSession().createCriteria(Manager.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Manager> managers =  (List<Manager>) criteria.list();
//        for(Manager man : managers)
//            man.setDepartment(null);
        return managers;
    }

    public void deleteManagerById(int id) {
        Manager manager = (Manager) getSession().createCriteria(Manager.class).add(Restrictions.eq("id", id)).uniqueResult();
        delete(manager);
    }

    public Manager findById(int id) {
        Criteria criteria = getSession().createCriteria(Manager.class).add(Restrictions.eq("id", id));
        return (Manager) criteria.uniqueResult();
    }

    public void updateManager(Manager manager){
        getSession().update(manager);
    }



}
