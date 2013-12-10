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

import com.shouwy.series.bdd.dao.face.SeriesDao;
import com.shouwy.series.bdd.model.Series;
import java.util.List;
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
public class SeriesDaoImpl implements SeriesDao{
    @Autowired
    private SessionFactory sessionFactory;
     
    @Override
    public void save(Series entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }
 
    @Override
    public void update(Series entity) {
        sessionFactory.getCurrentSession().update(entity);
    }
    
    @Override
    public Series merge(Series entity) {
        return (Series) sessionFactory.getCurrentSession().merge(entity);
    }
 
    @Override
    public void delete(Series entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }
 
    @Override
    public Series getById(Integer id) {
        return (Series) sessionFactory.getCurrentSession().get(Series.class, id);
    }
 
    @Override
    public List<Series> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Series").list();
    }
 
    @Override
    public int count() {
        return getAll().size();
    }
    
    @Override
    public List<Series> getByType(Integer type){
        return sessionFactory.getCurrentSession().createQuery("from Series Where idType="+type).list();
    }
}
