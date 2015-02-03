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
package com.shouwy.series.web.control.application;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Inspiron
 */
@Controller
public class HomeController {
    @Autowired
    SeriesDao seriesDao;
    @Autowired
    TypeDao typeDao;
    @Autowired
    EtatDao etatDao;
    @Autowired
    EtatPersonnelDao etatPersoDao;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView model = new ModelAndView("index");
        ArrayList<Type> listType = Util.initModelHeader(typeDao);
        model.addObject("listType", listType);
        model.addObject("mapEtat", Util.modelMapEtat(etatDao));
        model.addObject("mapEtatPerso", Util.modelMapEtatPerso(etatPersoDao));
        
        ArrayList<Series> listSeries = (ArrayList<Series>) seriesDao.getAll();
        HashMap<Type, Series> mapSeries = getRandSeries(listType, listSeries);
        model.addObject("mapSeries", mapSeries);
        
        return model;
    }

    private HashMap<Type, Series> getRandSeries(ArrayList<Type> listType, ArrayList<Series> listSeries) {
        HashMap<Type, Series> mapSeries = new HashMap<Type, Series>();
        HashMap<Integer, ArrayList<Series>> mapSeriesByType = Util.mapSeriesByIdType(listSeries); 
        for (Type t : listType){
            ArrayList<Series> list = mapSeriesByType.get(t.getId());
            Series s = null;
            if (list != null && !list.isEmpty()){
                s = list.get((int) (Math.random() * (list.size())));
            }
            mapSeries.put(t, s);
        }
        
        return mapSeries;
    }
}
