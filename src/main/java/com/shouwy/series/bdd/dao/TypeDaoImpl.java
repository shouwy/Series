/*
 * Copyright (C) 2013 Inspiron
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.shouwy.series.bdd.dao;

import com.shouwy.series.bdd.dao.face.TypeDao;
import com.shouwy.series.bdd.model.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Inspiron
 */
@Repository
@Transactional
public class TypeDaoImpl implements TypeDao{
    @Autowired
    private SessionFactory sessionFactory;
     
    @Override
    public void save(Type entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }
 
    @Override
    public void update(Type entity) {
        sessionFactory.getCurrentSession().update(entity);
    }
 
    @Override
    public void delete(Type entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }
 
    @Override
    public Type getById(Integer id) {
        return (Type) sessionFactory.getCurrentSession().get(Type.class, id);
    }
 
    @Override
    public List<Type> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Type").list();
    }
 
    @Override
    public int count() {
        return getAll().size();
    }
    @Override
    public Map<Integer, Type> getMapAll() {
        Map<Integer, Type> map = new HashMap<Integer, Type>();
        for (Type t : getAll()){
            map.put(t.getId(), t);
        }
        return map;
    }

    @Override
    public Type merge(Type entity) {
        return (Type) sessionFactory.getCurrentSession().merge(entity);
    }
}
