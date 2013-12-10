/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shouwy.series.bdd.dao.face;

import com.shouwy.series.bdd.model.Series;
import java.util.List;

/**
 *
 * @author Inspiron
 */

public interface SeriesDao{
    void save(Series entity);
    Series merge(Series entity);
    void update(Series entity);
    void delete(Series entity);
    Series getById(Integer id);
    List<Series> getAll();
    int count();
    List<Series> getByType(Integer type);
}