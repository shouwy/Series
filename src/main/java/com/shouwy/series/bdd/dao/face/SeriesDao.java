/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shouwy.series.bdd.dao.face;

import com.shouwy.series.bdd.dao.generic.GenericDao;
import com.shouwy.series.bdd.model.Series;
import java.util.List;

/**
 *
 * @author Inspiron
 */

public interface SeriesDao extends GenericDao<Series>{
    List<Series> getByType(Integer type);
}