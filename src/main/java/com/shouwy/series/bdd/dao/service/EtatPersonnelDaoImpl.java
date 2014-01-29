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

import com.shouwy.series.bdd.dao.face.EtatPersonnelDao;
import com.shouwy.series.bdd.model.EtatPersonnel;
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
public class EtatPersonnelDaoImpl implements EtatPersonnelDao{
    @Autowired
    private SessionFactory sessionFactory;
     
    @Override
    public void save(EtatPersonnel entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }
 
    @Override
    public EtatPersonnel merge(EtatPersonnel entity) {
        return (EtatPersonnel) sessionFactory.getCurrentSession().merge(entity);
    }
 
    @Override
    public void update(EtatPersonnel entity){
        sessionFactory.getCurrentSession().update(entity);
    }
    
    @Override
    public void delete(EtatPersonnel entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }
 
    @Override
    public EtatPersonnel getById(Integer id) {
        return (EtatPersonnel) sessionFactory.getCurrentSession().get(EtatPersonnel.class, id);
    }
 
    @Override
    public List<EtatPersonnel> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from EtatPersonnel").list();
    }
 
    @Override
    public int count() {
        return getAll().size();
    }

    @Override
    public Map<Integer, EtatPersonnel> getMapAll() {
        Map<Integer, EtatPersonnel> map = new HashMap<Integer, EtatPersonnel>();
        for (EtatPersonnel e : getAll()){
            map.put(e.getId(), e);
        }
        return map;
    }
}
