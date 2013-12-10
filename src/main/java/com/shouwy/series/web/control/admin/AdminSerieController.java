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
import com.shouwy.series.bdd.model.Saison;
import com.shouwy.series.bdd.model.Series;
import com.shouwy.series.web.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Inspiron
 */
@Controller
public class AdminSerieController {
    
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
    
    @RequestMapping(value="/admin/create", method = RequestMethod.POST)
    public ModelAndView create(HttpServletRequest request){
        Series s = new Series();
        
        s.setNom(request.getParameter("nom"));
        s.setSynopsis(request.getParameter("synopsis"));
        s.setIdType(Integer.parseInt(request.getParameter("type")));
        s.setIdEtat(1);
        s.setIdEtatPersonnel(1);
        
        seriesDao.save(s);
        AdminController admin = new AdminController();
        admin.typeDao = typeDao;
        admin.etatDao = etatDao;
        admin.etatPersoDao = etatPersoDao;
        admin.episodeDao = episodeDao;
        admin.saisonDao = saisonDao;    
        admin.seriesDao = seriesDao;
        return admin.list();
    }
    
    @RequestMapping(value="/admin/modif/{id}", method = RequestMethod.GET)
    public ModelAndView modif(@PathVariable Integer id){
        
        ModelAndView model = new ModelAndView("admin/serie/modif");
        model.addObject("listType", Util.initModelHeader(typeDao));
        model.addObject("mapEtat", Util.modelMapEtat(etatDao));
        model.addObject("mapEtatPerso", Util.modelMapEtatPerso(etatPersoDao));
        model.addObject("mapType", Util.modelMapType(typeDao));
               
        Series s = seriesDao.getById(id);       
        model.addObject("serie", s);
        
        ArrayList<Saison> listSaison = (ArrayList<Saison>) saisonDao.getBySeries(s);
        model.addObject("saison", listSaison);
        
        HashMap<Integer, ArrayList<Episode>> mapEpisodeByIdSaison = new HashMap<Integer, ArrayList<Episode>>();
        if (listSaison.size() != 0){
            ArrayList<Episode> listEpisode = new  ArrayList<Episode>();
            listEpisode = (ArrayList<Episode>) episodeDao.getByListSaison(listSaison);
            for (Saison saison : listSaison){
                ArrayList<Episode> episode = new ArrayList<Episode>();
                for (Episode e : listEpisode){
                    if (saison.getId().equals(e.getIdSaison())){
                        episode.add(e);
                    }
                }
                mapEpisodeByIdSaison.put(saison.getId(), episode);
            }
        }
        model.addObject("episode", mapEpisodeByIdSaison);
      
        return model;
    }
    
    @RequestMapping(value="/admin/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Integer id){
        
        Series s = seriesDao.getById(id);
        ArrayList<Saison> listSaison = (ArrayList<Saison>) saisonDao.getBySeries(s);
        if (listSaison.size() > 0 ){
            ArrayList<Episode> listEpisode = (ArrayList<Episode>) episodeDao.getByListSaison(listSaison);
            episodeDao.deleteList(listEpisode);
        }
        saisonDao.deleteList(listSaison);
        seriesDao.delete(s);
        
        AdminController admin = new AdminController();
        admin.typeDao = typeDao;
        admin.etatDao = etatDao;
        admin.etatPersoDao = etatPersoDao;
        admin.episodeDao = episodeDao;
        admin.saisonDao = saisonDao;    
        admin.seriesDao = seriesDao;
        return admin.list();
    }
}
