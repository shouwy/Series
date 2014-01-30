package com.shouwy.series.web.control.application.series;

import com.shouwy.series.bdd.dao.face.EtatDao;
import com.shouwy.series.bdd.dao.face.EtatPersonnelDao;
import com.shouwy.series.bdd.dao.face.SeriesDao;
import com.shouwy.series.bdd.dao.face.TypeDao;
import com.shouwy.series.bdd.model.Series;
import com.shouwy.series.bdd.model.Type;
import com.shouwy.series.web.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
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
@RequestMapping(value="/series")
public class SeriesController {

    @Autowired
    private SeriesDao seriesDao;
    @Autowired
    private TypeDao typeDao;
    @Autowired
    EtatDao etatDao;
    @Autowired
    EtatPersonnelDao etatPersoDao;
    
    @RequestMapping(value = "/list/{typeId}", method = RequestMethod.GET)
    public ModelAndView list(@PathVariable Integer typeId){
        
        ModelAndView model = new ModelAndView("series/list");
        model.addObject("listType", Util.initModelHeader(typeDao));
        model.addObject("mapEtat", Util.modelMapEtat(etatDao));
        model.addObject("mapEtatPerso", Util.modelMapEtatPerso(etatPersoDao));
        model.addObject("mapType", Util.modelMapType(typeDao));
        
        ArrayList<Series> listSeries = (ArrayList<Series>) seriesDao.getByType(typeId);
        model.addObject("list", listSeries);
        
        Type t = typeDao.getById(typeId);
        model.addObject("type", t);
                    
        return model;
    }
    @RequestMapping(value = "/affiche/{id}", method = RequestMethod.GET)
    public ModelAndView affiche(@PathVariable Integer id){
        
        ModelAndView model = new ModelAndView("series/affiche");   
        model.addObject("listType", Util.initModelHeader(typeDao));
        model.addObject("mapEtat", Util.modelMapEtat(etatDao));
        model.addObject("mapEtatPerso", Util.modelMapEtatPerso(etatPersoDao));
        model.addObject("mapType", Util.modelMapType(typeDao));
  
        Series s = seriesDao.getById(id);
        model.addObject("serie", s);
        
        return model;
    }
}
