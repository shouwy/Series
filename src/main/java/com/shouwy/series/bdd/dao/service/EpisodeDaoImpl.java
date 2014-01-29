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

import com.shouwy.series.bdd.dao.face.EpisodeDao;
import com.shouwy.series.bdd.model.Episode;
import com.shouwy.series.bdd.model.Saison;
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
public class EpisodeDaoImpl implements EpisodeDao{
    @Autowired
    private SessionFactory sessionFactory;
     
    @Override
    public void save(Episode entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }
 
    @Override
    public Episode merge(Episode entity) {
        return (Episode) sessionFactory.getCurrentSession().merge(entity);
    }
    
    @Override
    public void update(Episode entity){
        sessionFactory.getCurrentSession().update(entity);
    }
 
    @Override
    public void delete(Episode entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }
 
    @Override
    public Episode getById(Integer id) {
        return (Episode) sessionFactory.getCurrentSession().get(Episode.class, id);
    }
 
    @Override
    public List<Episode> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Episode").list();
    }
 
    @Override
    public int count() {
        return getAll().size();
    }

    @Override
    public List<Episode> getByListSaison(List<Saison> listSaison) {
        List<Integer> listId = new ArrayList<Integer>();
        for (Saison s : listSaison){
            listId.add(s.getId());
        }
        return getByListIdSaison(listId);    
    }

    @Override
    public List<Episode> getBySaison(Saison s) {
        ArrayList<Integer> listSeries = new ArrayList<Integer>();
        listSeries.add(s.getId());
        return getByListIdSaison(listSeries);
   }
    
    private List<Episode> getByListIdSaison(List<Integer> listId) {
        return sessionFactory.getCurrentSession().createQuery("from Episode Where idSaison IN ("+Util.convertCollectionToSQL(listId)+")").list();    
    }
    @Override
    public void deleteList(ArrayList<Episode> list){
        sessionFactory.getCurrentSession().delete(list);
    }
}
