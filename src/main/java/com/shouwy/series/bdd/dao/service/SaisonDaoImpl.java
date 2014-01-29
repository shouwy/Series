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

import com.shouwy.series.bdd.dao.face.SaisonDao;
import com.shouwy.series.bdd.model.Saison;
import com.shouwy.series.bdd.model.Series;
import com.shouwy.series.web.util.Util;
import java.util.ArrayList;
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
public class SaisonDaoImpl implements SaisonDao{
    @Autowired
    private SessionFactory sessionFactory;
     
    @Override
    public void save(Saison entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }
    
    @Override
    public Saison merge(Saison entity) {
        return (Saison) sessionFactory.getCurrentSession().merge(entity);
    }
 
    @Override
    public void update(Saison entity) {
        sessionFactory.getCurrentSession().update(entity);
    }
 
    @Override
    public void delete(Saison entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }
 
    @Override
    public Saison getById(Integer id) {
        return (Saison) sessionFactory.getCurrentSession().get(Saison.class, id);
    }
 
    @Override
    public List<Saison> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Saison").list();
    }
 
    @Override
    public int count() {
        return getAll().size();
    }

    @Override
    public List<Saison> getByListSeries(List<Series> listSeries) {
        List<Integer> listId = new ArrayList<Integer>();
        for (Series s : listSeries){
            listId.add(s.getId());
        }
        return getByListIdSeries(listId);
    }

    private List<Saison> getByListIdSeries(List<Integer> listId) {
        return sessionFactory.getCurrentSession().createQuery("from Saison Where idSerie IN ("+Util.convertCollectionToSQL(listId)+")").list();
    }

    @Override
    public List<Saison> getBySeries(Series s) {
        ArrayList<Integer> listSeries = new ArrayList<Integer>();
        listSeries.add(s.getId());
        return getByListIdSeries(listSeries);
   }
    
    @Override
    public void deleteList(ArrayList<Saison> list){
        sessionFactory.getCurrentSession().delete(list);
    }
}
