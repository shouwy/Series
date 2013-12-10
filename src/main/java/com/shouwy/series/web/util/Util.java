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
package com.shouwy.series.web.util;

import com.shouwy.series.bdd.dao.face.EtatDao;
import com.shouwy.series.bdd.dao.face.EtatPersonnelDao;
import com.shouwy.series.bdd.dao.face.TypeDao;
import com.shouwy.series.bdd.model.Etat;
import com.shouwy.series.bdd.model.EtatPersonnel;
import com.shouwy.series.bdd.model.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Inspiron
 */
public class Util {

    public static ArrayList<Type> initModelHeader(TypeDao typeDao) {
        ArrayList<Type> listType = (ArrayList<Type>) typeDao.getAll();
        return listType;
    }
    
    public static HashMap<Integer, Type> modelMapType(TypeDao typeDao) {
        HashMap<Integer, Type> listType = (HashMap<Integer, Type>) typeDao.getMapAll();
        return listType;
    }
    public static HashMap<Integer, Etat> modelMapEtat(EtatDao etatDao) {
        HashMap<Integer, Etat> listEtat = (HashMap<Integer, Etat>) etatDao.getMapAll();
        return listEtat;
    }
    public static HashMap<Integer, EtatPersonnel> modelMapEtatPerso(EtatPersonnelDao etatPersoDao) {
        HashMap<Integer, EtatPersonnel> listEtatPerso = (HashMap<Integer, EtatPersonnel>) etatPersoDao.getMapAll();
        return listEtatPerso;
    }

    public static String convertCollectionToSQL(List<Integer> listId) {
        String s = "";
        for (Integer i : listId){
            s += i+",";
        }
        
        return s.substring(0, s.lastIndexOf(","));
    }
    
    public static String safeDiv(Integer a, Integer b){
        String s = " - ";
        if (a != null && b != null && b != 0){
            double d = a / b;
            s = new DecimalFormat("0.00").format(d);
        }
        return s;
    }

    public static Calendar getStringInDate(String dateString) {
        String[] split = dateString.split("/");
        return new GregorianCalendar(Integer.parseInt(split[2]), Integer.parseInt(split[1])-1, Integer.parseInt(split[0]));
    }
    
    public static String getDateInString(Calendar date){
        return "";
    }
}
