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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Inspiron
 */
@Controller
public class AdminSaisonController {
    
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
    
    @RequestMapping(value="/admin/saisons/list")
    public ModelAndView list(){
        ModelAndView model = new ModelAndView("/admin/saison/list");
    
        model.addObject("listType", Util.initModelHeader(typeDao));
        ArrayList<Series> listSeries = (ArrayList<Series>) seriesDao.getAll();
        ArrayList<Saison> listSaison = (ArrayList<Saison>) saisonDao.getAll();

        HashMap<Saison, Series> mapSaison = new HashMap<Saison, Series>();
        for (Saison sa : listSaison){
            for (Series se : listSeries){
                if (se.getId().equals(sa.getIdSerie())){
                    mapSaison.put(sa, se);
                }
            }
        }
        model.addObject("listSaison", mapSaison);
        
        return model;
    }
    
    @RequestMapping(value="/admin/saisons/add", method = RequestMethod.POST)
    public ModelAndView addSaison(@RequestParam(value="idSeries", required=true) Integer id, 
                                  @RequestParam(value="start", required=true) String start,
                                  @RequestParam(value="end", required=true) String end,
                                  @RequestParam(value="nom", required=true) String nom){
        
        Saison s = new Saison();
        
        s.setIdSerie(id);
        s.setAnneeProduction(start+"-"+end);
        s.setNom(nom);
        
        saisonDao.save(s);
        AdminSerieController serieController = new AdminSerieController();
        serieController.typeDao = typeDao;
        serieController.etatDao = etatDao;
        serieController.etatPersoDao = etatPersoDao;
        serieController.episodeDao = episodeDao;
        serieController.saisonDao = saisonDao;
        serieController.seriesDao = seriesDao;
        return serieController.modif(id);
    }
    
    @RequestMapping(value="/admin/saisons/modif/{id}", method = RequestMethod.GET)
    public ModelAndView modif(@PathVariable Integer id){
        ModelAndView model = new ModelAndView("admin/saison/modif");
        
        Saison saison = saisonDao.getById(id);
        
        model.addObject("listType", Util.initModelHeader(typeDao));     
        model.addObject("saison", saison);        
        model.addObject("serie", seriesDao.getById(saison.getIdSerie()));
        
        return model;
    }
    
    @RequestMapping(value="/admin/saisons/valide", method = RequestMethod.POST)
    public ModelAndView valideModif(@RequestParam(value="id", required=true) Integer id, 
                                    @RequestParam(value="start", required=true) String start,
                                    @RequestParam(value="end", required=true) String end,
                                    @RequestParam(value="nom", required=true) String nom){
        
        Saison s = saisonDao.getById(id);
        s.setAnneeProduction(start+"-"+end);
        s.setNom(nom);
        saisonDao.update(s);
        
        AdminSerieController serieController = new AdminSerieController();
        serieController.typeDao = typeDao;
        serieController.etatDao = etatDao;
        serieController.etatPersoDao = etatPersoDao;
        serieController.episodeDao = episodeDao;
        serieController.saisonDao = saisonDao;
        serieController.seriesDao = seriesDao;
        return serieController.modif(s.getIdSerie());
    }
    
    @RequestMapping(value="/admin/saisons/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Integer id){
        
        Saison s = saisonDao.getById(id);
        ArrayList<Episode> listEpisode = (ArrayList<Episode>) episodeDao.getBySaison(s);
        episodeDao.deleteList(listEpisode);
        saisonDao.delete(s);
        
        AdminSerieController serieController = new AdminSerieController();
        serieController.typeDao = typeDao;
        serieController.etatDao = etatDao;
        serieController.etatPersoDao = etatPersoDao;
        serieController.episodeDao = episodeDao;
        serieController.saisonDao = saisonDao;
        serieController.seriesDao = seriesDao;
        return serieController.modif(s.getIdSerie());
    }
}
