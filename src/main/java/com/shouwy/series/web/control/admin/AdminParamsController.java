/*
 * Copyright (C) 2014 Inspiron
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

import com.shouwy.series.bdd.dao.face.EtatDao;
import com.shouwy.series.bdd.dao.face.EtatPersonnelDao;
import com.shouwy.series.bdd.dao.face.TypeDao;
import com.shouwy.series.bdd.model.Etat;
import com.shouwy.series.bdd.model.EtatPersonnel;
import com.shouwy.series.bdd.model.Type;
import com.shouwy.series.web.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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
public class AdminParamsController {
    @Autowired 
    TypeDao typeDao;
    @Autowired
    EtatDao etatDao;
    @Autowired
    EtatPersonnelDao etatPersoDao;
    
    @RequestMapping(value="/admin/params/list")
    public ModelAndView list(){
        ModelAndView model = new ModelAndView("admin/params/list");
        List<EtatPersonnel> listEtatPerso = etatPersoDao.getAll();
        model.addObject("listType", Util.initModelHeader(typeDao));
        model.addObject("etat", etatDao.getAll());
        model.addObject("etatPerso", listEtatPerso);
        
        for (EtatPersonnel e : listEtatPerso){
            System.err.println(e.getNom());
        }
        return model;
    }
    
    @RequestMapping(value="/admin/params/add", method = RequestMethod.POST)
    public ModelAndView add(@RequestParam(value="nom", required=true) String nom,
                            @RequestParam(value="type", required=true) String table){
        
        if (table.equals("etat")){
            Etat e = new Etat();
            e.setNom(nom);
            etatDao.save(e);
        }
        if (table.equals("etatPerso")){
            EtatPersonnel e = new EtatPersonnel();
            e.setNom(nom);
            etatPersoDao.save(e);
        }
        if (table.equals("type")){
            Type e = new Type();
            e.setNom(nom);
            typeDao.save(e);
        }
        
        return this.list();
        
    }
    @RequestMapping(value="/admin/params/delete/{table}/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String table, @PathVariable Integer id){
        if (table.equals("etat")){
            Etat e = etatDao.getById(id);
            etatDao.delete(e);
        }
        if (table.equals("etatPerso")){
            EtatPersonnel e = etatPersoDao.getById(id);
            etatPersoDao.delete(e);
        }
        if (table.equals("type")){
            Type e = typeDao.getById(id);
            typeDao.delete(e);
        }
        
        return this.list();
    }
    
    @RequestMapping(value="/admin/params/validate", method = RequestMethod.POST)
    public void validate(@RequestParam(value="value", required=true) String nom,
                        @RequestParam(value="id", required=true) String idElement,
                        HttpServletResponse response) throws IOException{
        String table = idElement.split("_")[0];
        Integer id = Integer.parseInt(idElement.split("_")[1]);
               
        if (table.equals("etat")){
            Etat e = etatDao.getById(id);
            e.setNom(nom);
            etatDao.save(e);
        }
        if (table.equals("etatPerso")){
            EtatPersonnel e = etatPersoDao.getById(id);
            e.setNom(nom);
            etatPersoDao.save(e);
        }
        if (table.equals("type")){
            Type e = typeDao.getById(id);
            e.setNom(nom);
            typeDao.save(e);
        }
        
       response.getOutputStream().write(nom.getBytes("UTF-8"));
    }
}
