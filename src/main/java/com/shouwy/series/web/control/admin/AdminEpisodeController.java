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
import java.util.Calendar;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
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
public class AdminEpisodeController {
 
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
    
    @RequestMapping(value="/admin/episodes/list")
    public ModelAndView list(){
        ModelAndView model = new ModelAndView("admin/episode/list");
        
        model.addObject("listType", Util.initModelHeader(typeDao));
        ArrayList<Episode> listEpisode = (ArrayList<Episode>) episodeDao.getAll();
        ArrayList<Series> listSeries = (ArrayList<Series>) seriesDao.getAll();
        ArrayList<Saison> listSaison = (ArrayList<Saison>) saisonDao.getAll();
        HashMap<Episode, HashMap<Saison, Series>> mapEpisode = new HashMap<Episode, HashMap<Saison, Series>>();
        
        for (Episode e : listEpisode){
            Integer idSaison = e.getIdSaison();
            for (Saison sa : listSaison){
                if (sa.getId().equals(idSaison)){
                    if (!mapEpisode.containsKey(e)){
                        HashMap<Saison, Series> mapSaison = new HashMap<Saison, Series>();
                        mapEpisode.put(e, mapSaison);
                    }
                    for (Series se : listSeries){
                        if (se.getId().equals(sa.getIdSerie())){
                            mapEpisode.get(e).put(sa, se);
                        }
                    }
                }
            }
        }
        
        model.addObject("listEpisode", mapEpisode);
        model.addObject("etat", etatPersoDao.getAll());
        
        return model;
    }
    
    @RequestMapping(value="/admin/episodes/add/{id}", method = RequestMethod.POST)
    public ModelAndView addEpisode(@PathVariable Integer id, 
                                   @RequestParam(value="titre", required=true) String titre,
                                   @RequestParam(value="saison", required=true) Integer idSaison,
                                   @RequestParam(value="synopsis", required=false) String synopsis,
                                   @RequestParam(value="etatperso", required=true) Integer idEtatPerso,
                                   @RequestParam(value="date", required=true) String dateString){
        
        Episode e = new Episode();
        
        e.setTitre(titre);
        e.setIdSaison(idSaison);
        e.setSynopsis(synopsis);
        e.setIdEtatPersonnel(idEtatPerso);
        Calendar date = Util.getStringInDate(dateString);
        e.setDateSortie(date);
        
        episodeDao.save(e);
        AdminSerieController serieController = new AdminSerieController();
        serieController.typeDao = typeDao;
        serieController.etatDao = etatDao;
        serieController.etatPersoDao = etatPersoDao;
        serieController.episodeDao = episodeDao;
        serieController.saisonDao = saisonDao;
        serieController.seriesDao = seriesDao;
        return serieController.modif(id);
    }
    
    @RequestMapping(value="/admin/episodes/modif/{id}", method = RequestMethod.GET)
    public ModelAndView modif(@PathVariable Integer id){
        ModelAndView model = new ModelAndView("admin/episode/modif");
        
        model.addObject("listType", Util.initModelHeader(typeDao));
        model.addObject("mapEtatPerso", Util.modelMapEtatPerso(etatPersoDao));
  
        Episode e = episodeDao.getById(id);
        Saison saison = saisonDao.getById(e.getIdSaison());
        Series s = seriesDao.getById(saison.getIdSerie());
       
        ArrayList<Saison> listSaison = (ArrayList<Saison>) saisonDao.getBySeries(s);
        
        model.addObject("episode", e);
        model.addObject("serie", s);
        model.addObject("saison", listSaison);
        
        return model;
    }
    
    @RequestMapping(value="/admin/episodes/valide/{id}", method = RequestMethod.POST)
    public ModelAndView valideModif(@PathVariable Integer id,
                                    @RequestParam(value="titre", required=true) String titre,
                                    @RequestParam(value="saison", required=true) Integer idSaison,
                                    @RequestParam(value="etatperso", required=true) Integer idEtatPerso,
                                    @RequestParam(value="date", required=true) String dateString,
                                    @RequestParam(value="synopsis", required=true) String synopsis,
                                    HttpServletRequest request){
        
        Episode e = episodeDao.getById(id);
        e.setIdSaison(idSaison);
        e.setTitre(titre);
        e.setIdEtatPersonnel(idEtatPerso);
        e.setSynopsis(synopsis);
        Calendar date = Util.getStringInDate(dateString);
        e.setDateSortie(date);
        
        episodeDao.update(e);
        
        request.setAttribute("msg", "Mise à jour de l'épisode réussi.");
        
        return this.modif(id);
    }
    
    @RequestMapping(value="/admin/episode/delete/{id}/{list}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Integer id, @PathVariable String list){
        
        Episode episode = episodeDao.getById(id);
        Saison saison = saisonDao.getById(episode.getIdSaison());
        
        episodeDao.delete(episode);
        
        if (list.equals("ep")){
            return this.list();
        }
        
        AdminSerieController serieController = new AdminSerieController();
        serieController.typeDao = typeDao;
        serieController.etatDao = etatDao;
        serieController.etatPersoDao = etatPersoDao;
        serieController.episodeDao = episodeDao;
        serieController.saisonDao = saisonDao;
        serieController.seriesDao = seriesDao;
        return serieController.modif(saison.getIdSerie());
    }
}
