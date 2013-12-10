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
package com.shouwy.series.web.control.admin;

import com.shouwy.series.bdd.dao.face.EpisodeDao;
import com.shouwy.series.bdd.dao.face.EtatDao;
import com.shouwy.series.bdd.dao.face.EtatPersonnelDao;
import com.shouwy.series.bdd.dao.face.SaisonDao;
import com.shouwy.series.bdd.dao.face.SeriesDao;
import com.shouwy.series.bdd.dao.face.TypeDao;
import com.shouwy.series.bdd.model.Episode;
import com.shouwy.series.bdd.model.Etat;
import com.shouwy.series.bdd.model.EtatPersonnel;
import com.shouwy.series.bdd.model.Saison;
import com.shouwy.series.bdd.model.Series;
import com.shouwy.series.bdd.model.Type;
import com.shouwy.series.web.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Inspiron
 */
@Controller
public class AdminController {
    
    @Autowired 
    TypeDao typeDao;
    @Autowired
    SeriesDao seriesDao;
    @Autowired
    SaisonDao saisonDao;
    @Autowired
    EpisodeDao episodeDao;
    @Autowired
    EtatDao etatDao;
    @Autowired
    EtatPersonnelDao etatPersoDao;
    
    @RequestMapping(value="/admin/admin")
    public ModelAndView index(){
    
        ModelAndView model = new ModelAndView("admin/admin");
        model.addObject("listType", Util.initModelHeader(typeDao));
        
        HashMap<Type, HashMap<String, Integer>> mapStat = new HashMap<Type, HashMap<String, Integer>>();
        for (Type t : (ArrayList<Type> ) typeDao.getAll()){
            HashMap<String, Integer> mapTmp = new HashMap<String, Integer>();
            ArrayList<Series> listSeries = (ArrayList<Series>) seriesDao.getByType(t.getId());
            mapTmp.put("series", listSeries.size());
            ArrayList<Saison> listSaison = new ArrayList<Saison>();
            Integer saisonSize = 0;       
            if (listSeries.size() != 0){
                listSaison = (ArrayList<Saison>) saisonDao.getByListSeries(listSeries);
                saisonSize = listSaison.size();
            }
            mapTmp.put("saisons", saisonSize);
            
            ArrayList<Episode> listEpisode = new  ArrayList<Episode>();
            Integer episodeSize = 0;
            if (listSaison.size() != 0){
                listEpisode = (ArrayList<Episode>) episodeDao.getByListSaison(listSaison);
                episodeSize = listEpisode.size();
            }
            mapTmp.put("episodes", episodeSize);
            
            mapStat.put(t, mapTmp);
        }
        model.addObject("mapStat", mapStat);
        
        return model;
    }
    
    @RequestMapping(value="/admin/list", method = RequestMethod.GET)
    public ModelAndView list(){
        
        ModelAndView model = new ModelAndView("admin/serie/list");
        model.addObject("listType", Util.initModelHeader(typeDao));
        
        ArrayList<Series> listSeries = (ArrayList<Series>) seriesDao.getAll();
        model.addObject("listSeries", listSeries);
        
        ArrayList<Type> type = (ArrayList<Type>) typeDao.getAll();
        model.addObject("type", type);
        
        ArrayList<Etat> etat = (ArrayList<Etat>) etatDao.getAll();
        model.addObject("etat", etat);
                
        ArrayList<EtatPersonnel> etatPerso = (ArrayList<EtatPersonnel>) etatPersoDao.getAll();
        model.addObject("etatPerso", etatPerso);
        return model;
    }
}
