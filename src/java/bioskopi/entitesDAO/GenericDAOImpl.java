/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskopi.entitesDAO;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import bioskopi.hibernate.util.HibernateUtil;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author profesor
 */
//public abstract class  GenericDAOImpl<T> implements GenericDAO<T> {
@Named
@Dependent
public class GenericDAOImpl<T> implements GenericDAO<T> {

    private Session session;
    
   
    @Override
    public T save(T obj) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            Object o = session.save(obj);
            t.commit();
            return (T) o;
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void persist(T obj) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.persist(obj);
            t.commit();

        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(T obj) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.update(obj);
            t.commit();
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public T merge(T obj) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            Object o = session.merge(obj);
            t.commit();
            return (T) o;
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void delete(T obj) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.delete(obj);
            t.commit();
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveOrUpdate(T obj) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.saveOrUpdate(obj);
            t.commit();
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<T> readAll(Class<T> c) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            List<T> list = session.createCriteria(c).list();
            return list;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public T readById(Class<T> c, String nameColumn, Long valueColumn) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            T obj = (T) session.createCriteria(c)
                    .add(Restrictions.eq(nameColumn, valueColumn))
                    .uniqueResult();
            return obj;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

}
