package DAO.Impl;

import DAO.SecurityDAO;
import com.alibaba.fastjson.JSONObject;
import model.SecurityEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SecurityDAOImpl implements SecurityDAO {
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<JSONObject> dimSearchByCode(String code) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String hql = "from SecurityEntity where SecurityEntity.secuCode like '" + code + "%'";
        Query query = s.createQuery(hql);
        List list = query.list();
        Iterator iterator = list.iterator();
        tx.commit();
        List<JSONObject> result = new ArrayList<>();
        while(iterator.hasNext()){
            SecurityEntity securityEntity = (SecurityEntity) iterator.next();
            JSONObject item = new JSONObject();
            item.put("code",securityEntity.getSecuCode());
            item.put("name", securityEntity.getSecuAbbr());
            result.add(item);
        }
        return result;
    }

    public List<JSONObject> dimSearchByName(String name) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String hql = "from SecurityEntity where SecurityEntity.secuAbbr like '%" + name + "%'";
        Query query = s.createQuery(hql);
        List list = query.list();
        Iterator iterator = list.iterator();
        tx.commit();
        List<JSONObject> result = new ArrayList<>();
        while(iterator.hasNext()){
            SecurityEntity securityEntity = (SecurityEntity) iterator.next();
            JSONObject item = new JSONObject();
            item.put("code",securityEntity.getSecuCode());
            item.put("name", securityEntity.getSecuAbbr());
            result.add(item);
        }
        return result;
    }

}
