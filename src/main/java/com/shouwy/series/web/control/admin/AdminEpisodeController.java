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
import com.shouwy.series.web.util.Util;
import java.util.Calendar;
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
    
    @RequestMapping(value="/admin/add/episode/{id}", method = RequestMethod.POST)
    public ModelAndView addEpisode(@PathVariable Integer id, HttpServletRequest request){
        
        Episode e = new Episode();
        
        e.setTitre(request.getParameter("titre"));
        e.setIdSaison(Integer.parseInt(request.getParameter("saison")));
        e.setSynopsis(request.getParameter("synopsis"));
        e.setIdEtatPersonnel(Integer.parseInt(request.getParameter("etatperso")));
        String dateString = request.getParameter("date");
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
    
    @RequestMapping(value="/admin/episode/modif/{id}", method = RequestMethod.GET)
    public ModelAndView modif(@PathVariable Integer id){
        ModelAndView model = new ModelAndView("admin/saison/modif");
        
        model.addObject("listType", Util.initModelHeader(typeDao));
        
        Episode s = episodeDao.getById(id);
        model.addObject("saison", s);
        return model;
    }
    
    @RequestMapping(value="admin/episode/valide/{id}", method = RequestMethod.POST)
    public ModelAndView valideModif(@PathVariable Integer id, HttpServletRequest request){
        
        Episode e = episodeDao.getById(id);
       
        episodeDao.update(e);
        
        return this.modif(id);
    }
    
    @RequestMapping(value="admin/episode/delete/{id}", method = RequestMethod.POST)
    public ModelAndView delete(@PathVariable Integer id){
        
        Episode episode = episodeDao.getById(id);
        Saison saison = saisonDao.getById(episode.getIdSaison());
        
        episodeDao.delete(episode);
        
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