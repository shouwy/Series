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
package com.shouwy.series.bdd.dao.service;

import com.shouwy.series.bdd.dao.face.EtatDao;
import com.shouwy.series.bdd.model.Etat;
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
public class EtatDaoImpl implements EtatDao{
    @Autowired
    private SessionFactory sessionFactory;
     
    @Override
    public void save(Etat entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }
 
    @Override
    public Etat merge(Etat entity) {
        return (Etat) sessionFactory.getCurrentSession().merge(entity);
    }
    
    @Override
    public void update(Etat entity){
        sessionFactory.getCurrentSession().update(entity);
    }
 
    @Override
    public void delete(Etat entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }
 
    @Override
    public Etat getById(Integer id) {
        return (Etat) sessionFactory.getCurrentSession().get(Etat.class, id);
    }
 
    @Override
    public List<Etat> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Etat").list();
    }
 
    @Override
    public int count() {
        return getAll().size();
    }

    @Override
    public Map<Integer, Etat> getMapAll() {
        Map<Integer, Etat> map = new HashMap<Integer, Etat>();
        for (Etat e : getAll()){
            map.put(e.getId(), e);
        }
        return map;
    }
}
