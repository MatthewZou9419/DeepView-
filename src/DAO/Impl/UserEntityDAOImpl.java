package DAO.Impl;

import DAO.UserEntityDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class UserEntityDAOImpl implements UserEntityDAO {

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean login(String name, String password) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String hql = "from UserEntity where name = " + name + " and password = " + password;
        Query query = s.createQuery(hql);
        List list = query.list();
        tx.commit();
        return list.size() > 0;
    }

}
