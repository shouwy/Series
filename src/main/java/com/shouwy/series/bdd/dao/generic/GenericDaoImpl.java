/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shouwy.series.bdd.dao.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Inspiron
 * @param <T>
 */
public class GenericDaoImpl<T> implements GenericDao<T> {
    @Autowired
    private SessionFactory sessionFactory;
 
    private final Class<T> entityClass;
    
    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        this.entityClass = (Class) pt.getActualTypeArguments()[0];
    }
    
    @Override
    public void save(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }
 
    @Override
    public T merge(T entity) {
        return (T) sessionFactory.getCurrentSession().merge(entity);
    }
 
    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }
 
    @Override
    public T getById(Long id) {
        return (T) sessionFactory.getCurrentSession().get(getEntityClass(), id);
    }
 
    @Override
    public List<T> getAll() {
        return createCriteria().list();
    }
 
    @Override
    public int count() {
        Criteria criteria = createCriteria();
        criteria.setProjection(Projections.rowCount());
        return ((Number) criteria.list().get(0)).intValue();
    }

    public Class<T> getEntityClass() {
        return this.entityClass;
    }
 
    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    
    protected Criteria createCriteria() {
        return sessionFactory.getCurrentSession().createCriteria(getEntityClass());
    }
}
