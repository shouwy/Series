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
import org.springframework.web.multipart.MultipartFile;
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
    
    @RequestMapping(value="/admin/series/list", method = RequestMethod.GET)
    public ModelAndView list(){        
        ModelAndView model = new ModelAndView("admin/serie/list");
        model.addObject("listType", Util.initModelHeader(typeDao));        
        model.addObject("listSeries", seriesDao.getAll());        
        model.addObject("type", typeDao.getAll());        
        model.addObject("etat", etatDao.getAll());                
        model.addObject("etatPerso", etatPersoDao.getAll());
        return model;
    }
    
    @RequestMapping(value="/admin/series/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam(value="image", required=false) MultipartFile image,
            @RequestParam(value="nom", required=false) String name,
            @RequestParam(value="synopsis", required=false) String synopsis,
            @RequestParam(value="type", required=false) Integer idType,
            @RequestParam(value="etat", required=false) Integer idEtat,
            @RequestParam(value="etatPerso", required=false) Integer idEtatPerso){
        Series s = new Series();
               
        s.setNom(name);
        s.setSynopsis(synopsis);
        s.setIdType(idType);
        s.setIdEtat(idEtat);
        s.setIdEtatPersonnel(idEtatPerso);
        
        String filename = "Default";
        if (image != null){
            filename = image.getOriginalFilename();
        }
        s.setImage(filename);
        
        seriesDao.save(s);

        return this.list();
    }
    
    @RequestMapping(value="/admin/series/modif/{id}", method = RequestMethod.GET)
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
        if (!listSaison.isEmpty()){
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
    
    @RequestMapping(value="/admin/series/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Integer id){
        
        Series s = seriesDao.getById(id);
        ArrayList<Saison> listSaison = (ArrayList<Saison>) saisonDao.getBySeries(s);
        if (listSaison.size() > 0 ){
            ArrayList<Episode> listEpisode = (ArrayList<Episode>) episodeDao.getByListSaison(listSaison);
            episodeDao.deleteList(listEpisode);
        }
        saisonDao.deleteList(listSaison);
        seriesDao.delete(s);
        
        return this.list();
    }
    
    @RequestMapping(value="/admin/series/modif/validate", method = RequestMethod.POST)
    public ModelAndView validate(@RequestParam(value="image", required=false) MultipartFile image,
            @RequestParam(value="nom", required=false) String name,
            @RequestParam(value="synopsis", required=false) String synopsis,
            @RequestParam(value="type", required=false) Integer idType,
            @RequestParam(value="etat", required=false) Integer idEtat,
            @RequestParam(value="etatPerso", required=false) Integer idEtatPerso,
            @RequestParam(value="id", required=true) Integer id){        
        
        Series s = seriesDao.getById(id);
        String filename = image.getOriginalFilename();
        
        s.setNom(name);
        s.setSynopsis(synopsis);
        s.setIdType(idType);
        s.setIdEtat(idEtat);
        s.setIdEtatPersonnel(idEtatPerso);
        
        seriesDao.update(s);
        return this.modif(s.getId());
    }
}
