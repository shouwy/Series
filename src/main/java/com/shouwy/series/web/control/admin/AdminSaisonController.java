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
    
    @RequestMapping(value="/admin/add/saison/{id}", method = RequestMethod.POST)
    public ModelAndView addSaison(@PathVariable Integer id, HttpServletRequest request){
        
        Saison s = new Saison();
        
        s.setIdSerie(id);
        s.setAnneeProduction(Integer.parseInt(request.getParameter("annee")));
        s.setNom(request.getParameter("nom"));
        
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
    
    @RequestMapping(value="admin/saison/modif/{id}", method = RequestMethod.GET)
    public ModelAndView modif(@PathVariable Integer id){
        ModelAndView model = new ModelAndView("admin/saison/modif");
        
        model.addObject("listType", Util.initModelHeader(typeDao));
        
        Saison saison = saisonDao.getById(id);
        model.addObject("saison", saison);
        
        Series serie = seriesDao.getById(saison.getIdSerie());
        model.addObject("serie", serie);
        
        return model;
    }
    
    @RequestMapping(value="admin/saison/valide/{id}", method = RequestMethod.POST)
    public ModelAndView valideModif(@PathVariable Integer id, HttpServletRequest request){
        
        Saison s = saisonDao.getById(id);
        s.setAnneeProduction(Integer.parseInt(request.getParameter("annee")));
        s.setNom(request.getParameter("nom"));
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
    
    @RequestMapping(value="admin/saison/delete/{id}", method = RequestMethod.GET)
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
